package com.lk.tools.log;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author luokun
 * @date 2019/4/23 15:10
 */
public class DailySizeRollingAppender extends FileAppender{
    static final int TOP_OF_TROUBLE = -1;
    static final int TOP_OF_MINUTE = 0;
    static final int TOP_OF_HOUR = 1;
    static final int HALF_DAY = 2;
    static final int TOP_OF_DAY = 3;
    static final int TOP_OF_WEEK = 4;
    static final int TOP_OF_MONTH = 5;

    /**
     * 单个日志文件最大大小
     */
    protected long maxFileSize = 10 * 1024 * 1024;
    /**
     * 单日期时间周期内的文件分割个数
     */
    protected int maxBackupIndex = 1;
    /**
     * 日期时间格式，按照此时间格式产生新的日志文件
     */
    private String datePattern = "yyyyMMdd";
    /**
     * "scheduledFilename" at the beginning of the next hour.
     */
    private String scheduledFilename;
    /**
     * 按照日期时间生成日志时，判定下次应生成文件
     */
    private long nextCheck = System.currentTimeMillis() - 1;
    private long previousCheck = 0;
    Date now = new Date();
    SimpleDateFormat sdf;
    RollingCalendar rc = new RollingCalendar();
    int checkPeriod = TOP_OF_TROUBLE;
    /**
     * 当前时区
     */
    static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
    /**
     * 缺省构造方法
     */
    public DailySizeRollingAppender() {
    }

    /**
     * 构造方法
     */
    public DailySizeRollingAppender(Layout layout, String filename,
                                    String datePattern) throws IOException {
        super(layout, filename, true);
        this.datePattern = datePattern;
        activateOptions();
    }

    public void activateOptions() {
        getFileName();
        super.activateOptions();
        if (datePattern != null && fileName != null) {
            now.setTime(System.currentTimeMillis());
            sdf = new SimpleDateFormat(datePattern);
            int type = computeCheckPeriod();
            printPeriodicity(type);
            rc.setType(type);
            scheduledFilename = fileName;
        } else {
            LogLog
                    .error("Either File or DatePattern options are not set for appender ["
                            + name + "].");
        }
    }

    public synchronized void setFile(String fileName, boolean append,
                                     boolean bufferedIO, int bufferSize) throws IOException {
        super.setFile(getFileName(), append, this.bufferedIO, this.bufferSize);
        if (append) {
            File f = new File(fileName);
            ((CountingQuietWriter) qw).setCount(f.length());
        }
    }

    protected void setQWForFiles(Writer writer) {
        this.qw = new CountingQuietWriter(writer, errorHandler);
    }

    public void sizeRollOver() {
        LogLog.debug("rolling over count=" + ((CountingQuietWriter) qw).getCount());
        LogLog.debug("maxBackupIndex=" + maxBackupIndex);
        getFileName();
        if (scheduledFilename.equals(fileName)) {
            return;
        }
        this.closeFile();
        try {
            this.setFile(fileName, fileAppend, bufferedIO, bufferSize);
        } catch (IOException e) {
            LogLog.error("setFile(" + fileName + ", false) call failed.", e);
        }
        scheduledFilename = fileName;
    }

    void timeRollOver() throws IOException {
        if (datePattern == null) {
            errorHandler.error("Missing DatePattern option in rollOver().");
            return;
        }
        getFileName();
        if (scheduledFilename.equals(fileName)) {
            return;
        }
        this.closeFile();
        try {
            this.setFile(fileName, fileAppend, this.bufferedIO, this.bufferSize);
        } catch (IOException e) {
            errorHandler.error("setFile(" + fileName + ", false) call failed.");
        }
        scheduledFilename = fileName;
    }

    protected void subAppend(LoggingEvent event) {
        long n = System.currentTimeMillis();
        if (n >= nextCheck||n<=previousCheck) {
            now.setTime(n);
            previousCheck = nextCheck;
            nextCheck = rc.getNextCheckMillis(now);
            try {
                timeRollOver();
            } catch (IOException ioe) {
                LogLog.error("rollOver() failed.", ioe);
            }
        } else if ((fileName != null)
                && ((CountingQuietWriter) qw).getCount() >= maxFileSize) {
            sizeRollOver();
        } else {
            //如果每次进入均需更改名称，则开启此else，加上if为佳。
            this.closeFile();
            try {
                this.setFile(fileName, fileAppend, bufferedIO, bufferSize);
            } catch (IOException e) {
                LogLog.error("setFile(" + fileName + ", false) call failed.", e);
            }
        }

        super.subAppend(event);
    }

    private String getFileName(){
        String fileName = "";
        int number = 1;
        outer:
        while (true) {
            if (maxBackupIndex>0) {
                //maxBackupIndex大于0，证明有文件限制
                if (maxBackupIndex>number) {
                    //如果maxBackupIndex大于number，证明未到最后一个文件
                    fileName = getFileRealName(number);
                    File file = new File(fileName);
                    //判断文件是否存在
                    if (file.exists()) {
                        //已存在，判断文件大小是否与超过最大文件大小限制值
                        if (file.length()>maxFileSize) {
                            //如已超过，则将number++，下次循环重新判断
                            number++;
                        }else {
                            //如未超过，则结束循环，返回fileName即可
                            break outer;
                        }
                    }else {
                        //未存在，结束循环，返回fileName
                        break outer;
                    }
                }else if (maxBackupIndex<=number) {
                    //证明已是最后一个文件
                    fileName = getFileRealName(number);
                    File file = new File(fileName);
                    //判断文件是否存在
                    if (file.exists()) {
                        //已存在，判断文件大小是否与超过最大文件大小限制值
                        if (file.length()>maxFileSize) {
                            //如已超过，将第一个文件删除后，其他文件名更改成上一个文件名然后结束循环
                            //删除第一个文件
                            File tempFile = new File(getFileRealName(1));
                            if (tempFile.exists()) {
                                tempFile.delete();
                            }
                            //修改文件名
                            for (int i = 1; i <= maxBackupIndex; i++) {
                                //将要修改的文件名
                                tempFile = new File(getFileRealName(i-1));
                                //准备修改的文件名
                                file = new File(getFileRealName(i));
                                this.closeFile();
                                file.renameTo(tempFile);
                            }
                            //修改完毕后，重新定义将要写入日志的文件名
                            fileName = getFileRealName(maxBackupIndex);
                            //创建新的文件
                            try {
                                super.setFile(fileName, fileAppend, bufferedIO, bufferSize);
                            } catch (IOException e) {
                                LogLog.error("setFile(" + fileName + ", false) call failed.", e);
                            }
                            break outer;
                        }else {
                            //如未超过，直接结束循环返回fileName
                            break outer;
                        }
                    }else {
                        //未存在，结束循环，返回fileName
                        break outer;
                    }
                }
            }else if(maxBackupIndex<0) {
                //maxBackupIndex小于0，证明没有文件限制
                //获得当前文件应有的最大名称
                File file;
                for (int i = number; i < Integer.MAX_VALUE; i++) {
                    fileName = getFileRealName(i);
                    file = new File(fileName);
                    //判断文件是否存在
                    if (file.exists()) {
                        //如果存在判断大小
                        if (file.length()>maxFileSize) {
                            //如果当前文件大小以大于最大文件大小限制，继续循环
                        }else {
                            //结束循环直接返回文件名
                            break outer;
                        }
                    }else {
                        //如果不存在直接返回文件名
                        break outer;
                    }
                }
            }
        }
        this.fileName = fileName;
        return fileName;
    }

    private String getFileRealName(int number){
        //获得线程值
        String fileName = fileNameFormat;
        int d = 1;
        while (d!=-1) {
            d=fileName.indexOf("%d");
            if (d>0) {
                SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
                String date = sdf.format(new Date());
                fileName = fileName.substring(0, d)+date+fileName.substring(d+2);
            }
            d=fileName.indexOf("%N");
            if (d>0) {
                int begin = fileName.indexOf("{", d);
                int end = fileName.indexOf("}", d);
                String dateFormat = fileName.substring(begin+1, end);
                String str = String.format("%0"+dateFormat.length()+"d", number );
                fileName = fileName.substring(0, d)+str+fileName.substring(end+1);
            }
        }
        return fileName;
    }

    void printPeriodicity(int type) {
        switch (type) {
            case TOP_OF_MINUTE:
                LogLog.debug("Appender [" + name + "] to be rolled every minute.");
                break;
            case TOP_OF_HOUR:
                LogLog
                        .debug("Appender [" + name + "] to be rolled on top of every hour.");
                break;
            case HALF_DAY:
                LogLog.debug("Appender [" + name
                        + "] to be rolled at midday and midnight.");
                break;
            case TOP_OF_DAY:
                LogLog.debug("Appender [" + name + "] to be rolled at midnight.");
                break;
            case TOP_OF_WEEK:
                LogLog.debug("Appender [" + name + "] to be rolled at start of week.");
                break;
            case TOP_OF_MONTH:
                LogLog.debug("Appender [" + name
                        + "] to be rolled at start of every month.");
                break;
            default:
                LogLog.warn("Unknown periodicity for appender [" + name + "].");
        }
    }
    int computeCheckPeriod() {
        RollingCalendar rollingCalendar = new RollingCalendar(gmtTimeZone,
                Locale.ENGLISH);
        Date epoch = new Date(0);
        if (datePattern != null) {
            for (int i = TOP_OF_MINUTE; i <= TOP_OF_MONTH; i++) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
                simpleDateFormat.setTimeZone(gmtTimeZone);
                String r0 = simpleDateFormat.format(epoch);
                rollingCalendar.setType(i);
                Date next = new Date(rollingCalendar.getNextCheckMillis(epoch));
                String r1 = simpleDateFormat.format(next);
                if (r0 != null && r1 != null && !r0.equals(r1)) {
                    return i;
                }
            }
        }
        return TOP_OF_TROUBLE;
    }

    public long getMaximumFileSize() {
        return maxFileSize;
    }

    public void setMaximumFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public void setMaxFileSize(String value) {
        maxFileSize = OptionConverter.toFileSize(value, maxFileSize + 1);
    }

    public int getMaxBackupIndex() {
        return maxBackupIndex;
    }

    public void setMaxBackupIndex(int maxBackups) {
        this.maxBackupIndex = maxBackups;
    }

    public void setDatePattern(String pattern) {
        datePattern = pattern;
    }

    public String getDatePattern() {
        return datePattern;
    }

    class RollingCalendar extends GregorianCalendar {

        private static final long serialVersionUID = 3555119026655946216L;

        int type = DailySizeRollingAppender.TOP_OF_TROUBLE;

        RollingCalendar() {
            super();
        }

        RollingCalendar(TimeZone tz, Locale locale) {
            super(tz, locale);
        }

        void setType(int type) {
            this.type = type;
        }

        public long getNextCheckMillis(Date now) {
            return getNextCheckDate(now).getTime();
        }

        public Date getNextCheckDate(Date now) {
            this.setTime(now);
            switch (type) {
                case DailySizeRollingAppender.TOP_OF_MINUTE:
                    this.set(Calendar.SECOND, 0);
                    this.set(Calendar.MILLISECOND, 0);
                    this.add(Calendar.MINUTE, 1);
                    break;
                case DailySizeRollingAppender.TOP_OF_HOUR:
                    this.set(Calendar.MINUTE, 0);
                    this.set(Calendar.SECOND, 0);
                    this.set(Calendar.MILLISECOND, 0);
                    this.add(Calendar.HOUR_OF_DAY, 1);
                    break;
                case DailySizeRollingAppender.HALF_DAY:
                    this.set(Calendar.MINUTE, 0);
                    this.set(Calendar.SECOND, 0);
                    this.set(Calendar.MILLISECOND, 0);
                    int hour = get(Calendar.HOUR_OF_DAY);
                    if (hour < 12) {
                        this.set(Calendar.HOUR_OF_DAY, 12);
                    } else {
                        this.set(Calendar.HOUR_OF_DAY, 0);
                        this.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    break;
                case DailySizeRollingAppender.TOP_OF_DAY:
                    this.set(Calendar.HOUR_OF_DAY, 0);
                    this.set(Calendar.MINUTE, 0);
                    this.set(Calendar.SECOND, 0);
                    this.set(Calendar.MILLISECOND, 0);
                    this.add(Calendar.DATE, 1);
                    break;
                case DailySizeRollingAppender.TOP_OF_WEEK:
                    this.set(Calendar.DAY_OF_WEEK, getFirstDayOfWeek());
                    this.set(Calendar.HOUR_OF_DAY, 0);
                    this.set(Calendar.SECOND, 0);
                    this.set(Calendar.MILLISECOND, 0);
                    this.add(Calendar.WEEK_OF_YEAR, 1);
                    break;
                case DailySizeRollingAppender.TOP_OF_MONTH:
                    this.set(Calendar.DATE, 1);
                    this.set(Calendar.HOUR_OF_DAY, 0);
                    this.set(Calendar.SECOND, 0);
                    this.set(Calendar.MILLISECOND, 0);
                    this.add(Calendar.MONTH, 1);
                    break;
                default:
                    throw new IllegalStateException("Unknown periodicity type.");
            }
            return getTime();
        }
    }
}
