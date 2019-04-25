/**
 * Copyright 2017 zsy.com Inc. All Rights Reserved.
 * @Title DateUtils.java
 * @Package com.wcs.tools.date
 * @Description: TODO:
 * @author eric
 * @date 2017年6月16日下午12:00:32
 * @version V1.0
 */
package com.lk.tools.date;

import com.lk.tools.string.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title DateUtils.java
 * @Package com.wcs.tools.date
 * @Description: TODO:
 * @author eric
 * @date 2017年6月16日下午12:00:32
 * @version V1.0
 */
public class DateUtils {

    public final static int F_S_MILLISECOND_TO_SECOND = 1000;
    public final static int F_S_SECOND_TO_MINUTE = 60;
    public final static int F_S_MINUTE_TO_HOUR = 60;
    public final static int F_S_HOUR_TO_DAY = 24;
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    private static SimpleDateFormat simpleDateFormat;
    private static Calendar calendar;


    public static Calendar getCalendar(){
        return Calendar.getInstance();
    }



    public static String format(Date date,String pattern){
        if(date == null){
            return "";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
    /**
     * 是否为给定格式的日期
     * @param date
     * @return
     */
    public static boolean isValidDate(String date){
        for(String pattern :parsePatterns){
            boolean validDate = isValidDate(pattern, date);
            if(validDate){
                return true;
            }
        }
        return false;
    }

    public static Date getCommonDate(String date) throws ParseException{

        for(String pattern :parsePatterns){
            boolean validDate = isValidDate(pattern, date);
            if(validDate){
                simpleDateFormat = new SimpleDateFormat(pattern);
                return simpleDateFormat.parse(date);
            }
        }
        return null;
    }

    /**
     * 是否为给定格式的日期
     * @param pattern
     * @param date
     * @return
     */
    public static boolean isValidDate(String pattern,String date){
        if(StringUtils.isEmpty(date)){
            return false;
        }
        try {
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern);
            simpleDateFormat.parse(date);
            return true;
        }catch (Exception ex){
            return false;
        }

    }
    /**
     * 获得当前的日期时间
     * @author eric
     * @date 2017年6月1日上午9:58:26
     * @return Date 当前的日期时间
     */
    public static Date getDate(){
        return new Date();
    }

    /**
     * 获得1970-01-01 00:00:00至当前时间的总毫秒数
     * @author eric
     * @date 2017年6月1日上午10:05:31
     * @return Long 当前时间的毫秒数
     */
    public static long getMilliseconds(){
        return new Date().getTime();
    }

    /**
     * 获得当前日期时间指定格式的日期时间字符串
     * @author eric
     * @date 2017年6月1日上午9:46:39
     * @param format  日期时间字符串格式如 "yyyy-MM-dd HH:mm:ss SSS"
     * @return String  符合格式要求的当前的日期时间的字符串
     */
    public static String getDate(String format){
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获得指定日期时间指定格式的日期时间字符串
     * @author eric
     * @date 2017年6月1日上午10:15:55
     * @param format 日期时间字符串格式如 "yyyy-MM-dd HH:mm:ss SSS"
     * @param date 指定的日期时间
     * @return String 符合格式要求的指定日期时间的字符串
     */
    public static String getDate(String format, Date date){
        simpleDateFormat=new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 获得指定日期时间字符串的日期时间格式
     * @author eric
     * @date 2017年6月1日上午10:24:24
     * @param format 字符串strDate的日期时间格式如 "yyyy-MM-dd HH:mm:ss SSS"
     * @param date 指定的字符串格式的日期时间，格式需与format相同
     * @return Date 符合要求的指定日期时间类型
     * @throws ParseException 格式化错误
     */
    public static Date getDate(String format, String date) throws ParseException{
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

    /**
     * 获得1970-01-01 00:00:00至设置时间的总毫秒数
     * @author eric
     * @date 2017年6月1日下午1:35:33
     * @param format 字符串strDate的日期时间格式如 "yyyy-MM-dd HH:mm:ss SSS"
     * @param strDate 指定的字符串格式的日期时间，格式需与format相同
     * @return long 当前时间的毫秒数
     * @throws ParseException 格式化错误
     */
    public static long getMilliseconds(String format, String date) throws ParseException{
        simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date).getTime();
    }

    /**
     * 获得1970-01-01 00:00:00至设置时间的总毫秒数
     * @author eric
     * @date 2017年6月2日上午9:58:41
     * @param format 字符串strDate的日期时间格式如 "yyyy-MM-dd HH:mm:ss SSS"
     * @param strDate 指定的字符串格式的日期时间，格式需与format相同
     * @return long 当前时间的毫秒数
     * @throws ParseException 格式化错误
     */
    public static long getMilliseconds(Date date){
        return date.getTime();
    }

    /**
     * 获取当前年份
     * @author eric
     * @date 2017年6月1日上午10:31:37
     * @return int 当前年份
     */
    public static int getYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取指定日期时间年份
     * @author eric
     * @date 2017年6月1日上午11:29:16
     * @param date 指定日期时间
     * @return int 指定日期年份
     */
    public static int getYear(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取指定日期时间年份
     * @author eric
     * @date 2017年6月2日上午10:02:43
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 指定日期年份
     * @throws ParseException 格式化错误
     */
    public static int getYear(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     * @author eric
     * @date 2017年6月1日上午10:32:32
     * @return int 当前月份
     */
    public static int getMonth(){
        return Calendar.getInstance().get(Calendar.MONTH)+1;
    }

    /**
     * 获取指定日期时间月份
     * @author eric
     * @date 2017年6月1日上午11:34:22
     * @param date 指定日期时间
     * @return int 指定日期时间月份
     */
    public static int getMonth(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH)+1;
    }

    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取指定日期时间月份
     * @author eric
     * @date 2017年6月1日上午11:34:22
     * @param date 指定日期时间
     * @return int 指定日期时间月份
     */
    public static int getMonthSpace(Date beginTime,Date endTime){
        calendar = Calendar.getInstance();
        calendar.setTime(beginTime);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(endTime);

        int nYear = (calendar2.get(Calendar.YEAR)-calendar.get(Calendar.YEAR))*12;
        int nMonth = calendar2.get(Calendar.MONTH)-calendar.get(Calendar.MONTH);
        int nMonthSpace = nYear>0?nYear+nMonth:nMonth;

        int nDay = getDaysByYearMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        nMonthSpace = nDay-calendar.get(Calendar.DATE)+calendar2.get(Calendar.DATE)>=
                nDay?nMonthSpace+1:nMonthSpace;

        return nMonthSpace;
    }

    /**
     * 获取指定日期时间月份
     * @author eric
     * @date 2017年6月2日上午10:03:58
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 指定日期时间月份
     * @throws ParseException 格式化错误
     */
    public static int getMonth(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.MONTH)+1;
    }

    /**
     * 获得当前周所处于本年度的第几周
     * @author eric
     * @date 2017年6月1日上午10:40:49
     * @return int 返回年度周
     */
    public static int getWeekOfYear(){
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获得指定日期时间周所处于时间所属年度的第几周
     * @author eric
     * @date 2017年6月1日下午12:06:42
     * @param date 指定日期时间
     * @return int 返回指定日期时间年度周
     */
    public static int getWeekOfYear(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获得指定日期时间周所处于时间所属年度的第几周
     * @author eric
     * @date 2017年6月2日上午10:05:16
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 返回指定日期时间年度周
     * @throws ParseException 格式化错误
     */
    public static int getWeekOfYear(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获得当前周所处于本月度的第几周
     * @author eric
     * @date 2017年6月1日上午10:48:41
     * @return int 返回月度周
     */
    public static int getWeekOfMonth(){
        return Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获得指定日期时间周所处于时间所属月度的第几周
     * @author eric
     * @date 2017年6月1日下午12:07:14
     * @param date 指定日期时间
     * @return int 返回指定日期时间月度周
     */
    public static int getWeekOfMonth(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获得指定日期时间周所处于时间所属月度的第几周
     * @author eric
     * @date 2017年6月2日上午10:09:21
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 返回指定日期时间月度周
     * @throws ParseException 格式化错误
     */
    public static int getWeekOfMonth(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取当前日期
     * @author eric
     * @date 2017年6月1日上午10:32:41
     * @return int 当前日期
     */
    public static int getDay(){
        return Calendar.getInstance().get(Calendar.DATE);
    }

    /**
     * 获取指定日期时间日期
     * @author eric
     * @date 2017年6月1日下午12:08:58
     * @param date 指定日期时间
     * @return int 指定日期时间日期
     */
    public static int getDay(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取指定日期时间日期
     * @author eric
     * @date 2017年6月2日上午10:10:27
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 指定日期时间日期
     * @throws ParseException 格式化错误
     */
    public static int getDay(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获得当前日所处于本月度的第几日
     * @author eric
     * @date 2017年6月1日上午10:50:37
     * @return int 返回月度日
     */
    public static int getDayOfMonth(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得指定日期时间所处于本月度的第几日
     * @author eric
     * @date 2017年6月1日下午12:09:29
     * @param date 指定日期时间
     * @return int 返回指定日期时间所处于的月度日
     */
    public static int getDayOfMonth(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得指定日期时间所处于本月度的第几日
     * @author eric
     * @date 2017年6月2日上午10:11:38
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 返回指定日期时间所处于的月度日
     * @throws ParseException 格式化错误
     */
    public static int getDayOfMonth(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得当前日所处于本年度的第几日
     * @author eric
     * @date 2017年6月1日上午10:50:40
     * @return int 返回年度日
     */
    public static int getDayOfYear(){
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获得指定日期时间所处于本年度的第几日
     * @author eric
     * @date 2017年6月1日下午12:10:15
     * @param date 指定日期时间
     * @return int 返回指定日期时间所处于的年度日
     */
    public static int getDayOfYear(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获得指定日期时间所处于本年度的第几日
     * @author eric
     * @date 2017年6月2日上午10:12:59
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 返回指定日期时间所处于的月度日
     * @throws ParseException 格式化错误
     */
    public static int getDayOfYear(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获得当前日所处于本周的第几日，周一为1，周日为7，以此类推
     * @author eric
     * @date 2017年6月1日上午10:52:37
     * @return int 返回当前周第几日
     */
    public static int getDayOfWeek(){
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;
        if (day == 0) {
            day = 7;
        }
        return day;
    }

    /**
     * 获得指定日所处于本周的第几日，周一为1，周日为7，以此类推
     * @author eric
     * @date 2017年6月1日下午12:11:37
     * @param date 指定日期时间
     * @return int 返回当前周第几日
     */
    public static int getDayOfWeek(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (day == 0) {
            day = 7;
        }
        return day;
    }

    /**
     * 获得指定日所处于本周的第几日，周一为1，周日为7，以此类推
     * @author eric
     * @date 2017年6月2日上午10:16:00
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 返回当前周第几日
     * @throws ParseException 格式化错误
     */
    public static int getDayOfWeek(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        int day = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (day == 0) {
            day = 7;
        }
        return day;
    }

    /**
     * 获得当前日所处于以当前月计算的第几周，指定月1~7日为1，8~14日为2，以此类推
     * @author eric
     * @date 2017年6月1日上午10:59:08
     * @return int 当前日所处于本月的第几周
     */
    public static int getDayOfWeekInMonth(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * 获得指定日所处于以指定月计算的第几周，指定月1~7日为1，8~14日为2，以此类推
     * @author eric
     * @date 2017年6月1日下午1:09:15
     * @param date 指定日期时间
     * @return int 指定日所处于指定月的第几周
     */
    public static int getDayOfWeekInMonth(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * 获得指定日所处于以指定月计算的第几周，指定月1~7日为1，8~14日为2，以此类推
     * @author eric
     * @date 2017年6月2日上午10:20:51
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 指定日所处于指定月的第几周
     * @throws ParseException 格式化错误
     */
    public static int getDayOfWeekInMonth(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * 获得当前时间所处于本日的上午下午，0~12点为上午13~0为下午
     * @author eric
     * @date 2017年6月1日上午11:03:15
     * @return int 0为上午，1为下午
     */
    public static int getAmPm(){
        return Calendar.getInstance().get(Calendar.AM_PM);
    }

    /**
     * 获得指定时间所处于指定日的上午下午，0~12点为上午13~0为下午
     * @author eric
     * @date 2017年6月1日下午1:14:22
     * @param date 指定日期时间
     * @return int 0为上午，1为下午
     */
    public static int getAmPm(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.AM_PM);
    }

    /**
     * 获得指定时间所处于指定日的上午下午，0~12点为上午13~0为下午
     * @author eric
     * @date 2017年6月2日上午10:21:45
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 0为上午，1为下午
     * @throws ParseException 格式化错误
     */
    public static int getAmPm(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.AM_PM);
    }

    /**
     * 获得当前小时，12小时制
     * @author eric
     * @date 2017年6月1日上午11:06:31
     * @return int 当前小时
     */
    public static int getHour(){
        return Calendar.getInstance().get(Calendar.HOUR);
    }

    /**
     * 获得指定日期时间的小时，12小时制
     * @author eric
     * @date 2017年6月1日下午1:15:21
     * @param date 指定日期时间
     * @return int 指定日期的小时
     */
    public static int getHour(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 获得指定日期时间的小时，12小时制
     * @author eric
     * @date 2017年6月2日上午10:22:57
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 指定日期的小时
     * @throws ParseException 格式化错误
     */
    public static int getHour(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 获得当前小时，24小时制
     * @author eric
     * @date 2017年6月1日上午11:06:47
     * @return int 当前小时
     */
    public static int getHourOfDay(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获得指定日期时间的小时，24小时制
     * @author eric
     * @date 2017年6月1日下午1:16:20
     * @param date 指定日期时间
     * @return int 当前小时
     */
    public static int getHourOfDay(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获得指定日期时间的小时，24小时制
     * @author eric
     * @date 2017年6月2日上午10:23:50
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 当前小时
     * @throws ParseException 格式化错误
     */
    public static int getHourOfDay(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获得当前分钟
     * @author eric
     * @date 2017年6月1日上午11:08:04
     * @return int 当前分钟
     */
    public static int getMinute(){
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    /**
     * 获得指定时间的分钟
     * @author eric
     * @date 2017年6月1日下午1:17:30
     * @param date 指定日期时间
     * @return int 指定时间的分钟
     */
    public static int getMinute(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获得指定时间的分钟
     * @author eric
     * @date 2017年6月2日上午10:25:10
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 指定时间的分钟
     * @throws ParseException 格式化错误
     */
    public static int getMinute(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获得当前秒
     * @author eric
     * @date 2017年6月1日上午11:08:17
     * @return int 当前秒
     */
    public static int getSecond(){
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    /**
     * 获得指定时间的秒
     * @author eric
     * @date 2017年6月1日下午1:18:18
     * @param date 指定日期时间
     * @return int 指定时间的秒
     */
    public static int getSecond(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获得指定时间的秒
     * @author eric
     * @date 2017年6月2日上午10:26:37
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 指定时间的秒
     * @throws ParseException 格式化错误
     */
    public static int getSecond(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获得当前毫秒
     * @author eric
     * @date 2017年6月1日上午11:09:10
     * @return int 当前毫秒
     */
    public static int getMillisecond(){
        return Calendar.getInstance().get(Calendar.MILLISECOND);
    }

    /**
     * 获得指定日期时间的毫秒
     * @author eric
     * @date 2017年6月1日下午1:19:34
     * @param date 指定日期时间
     * @return int 指定日期时间的毫秒
     */
    public static int getMillisecond(Date date){
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MILLISECOND);
    }

    /**
     * 获得指定日期时间的毫秒
     * @author eric
     * @date 2017年6月2日上午10:28:09
     * @param date 指定日期时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 指定日期时间的毫秒
     * @throws ParseException 格式化错误
     */
    public static int getMillisecond(String date, String format) throws ParseException{
        calendar = Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.get(Calendar.MILLISECOND);
    }

    /**
     * 获得当前时区偏移量，单位毫秒
     * @author eric
     * @date 2017年6月1日上午11:21:08
     * @return int 时区偏移量，毫秒
     */
    public static int getZoneOffset(){
        return Calendar.getInstance().get(Calendar.ZONE_OFFSET);
    }

    /**
     * 获得当前时区的夏令时偏移量，单位毫秒
     * @author eric
     * @date 2017年6月1日上午11:24:44
     * @return int 夏令时偏移量，单位毫秒
     */
    public static int getDstOffset(){
        return Calendar.getInstance().get(Calendar.DST_OFFSET);
    }

    /**
     * 获取当前日期时间之前或者之后某一年的日期
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param year 与当前年对比，负数为之前，正数为之后，当前为0
     * @return Date 日期
     */
    public static Date getYear(int year){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 获取当前日期时间之前或者之后某一年的日期
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param year 与当前年对比，负数为之前，正数为之后，当前为0
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getYear(int year, String format){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR, year);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一年的日期
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param year 与当前年对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date getYear(int year,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一年的日期
     * @author eric
     * @date 2017年6月2日上午10:29:58
     * @param year 与当前年对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException 格式化错误
     */
    public static Date getYear(int year, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一年的日期
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param year 与当前年对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getYear(int year, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一年的日期
     * @author eric
     * @date 2017年6月2日上午10:46:12
     * @param year 与当前年对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException 格式化错误
     */
    public static String getYear(int year, String date, String dateFormat,
                                 String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.add(Calendar.YEAR, year);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 获取当前日期时间之前或者之后某一月的日期
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @return Date 日期
     */
    public static Date getMonth(int month){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获取当前日期时间之前或者之后某一月的日期
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getMonth(int month, String format){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一月的日期
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date getMonth(int month,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一月的日期
     * @author eric
     * @date 2017年6月2日上午10:48:35
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException
     */
    public static Date getMonth(int month, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一月的日期
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getMonth(int month, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一月的日期
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException
     */
    public static String getMonth(int month, String date, String dateFormat,
                                  String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.add(Calendar.MONTH, month);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 获取当前日期时间之前或者之后某一日的日期
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param day 与当前日对比，负数为之前，正数为之后，当前为0
     * @return Date 日期
     */
    public static Date getDay(int day){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 获取当前日期时间之前或者之后某一日的日期
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param day 与当前日对比，负数为之前，正数为之后，当前为0
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getDay(int day, String format){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一日的日期
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param day 与当前日对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date getDay(int day,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一日的日期
     * @author eric
     * @date 2017年6月2日上午10:50:47
     * @param day 与当前日对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException 格式化错误
     */
    public static Date getDay(int day, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一日的日期
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param day 与当前日对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getDay(int day, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一日的日期
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param day 与当前日对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException
     */
    public static String getDay(int day, String date, String dateFormat,
                                String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.add(Calendar.DATE, day);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 获取当前日期时间之前或者之后某一小时的日期
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param hour 与当前小时对比，负数为之前，正数为之后，当前为0
     * @return Date 日期
     */
    public static Date getHour(int hour){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 获取当前日期时间之前或者之后某一小时的日期
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param hour 与当前小时对比，负数为之前，正数为之后，当前为0
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getHour(int hour, String format){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一小时的日期
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param hour 与当前小时对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date getHour(int hour,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一小时的日期
     * @author eric
     * @date 2017年6月2日上午10:52:41
     * @param hour 与当前小时对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException 格式化错误
     */
    public static Date getHour(int hour, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一小时的日期
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param hour 与当前小时对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getHour(int hour, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一小时的日期
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param hour 与当前小时对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException
     */
    public static String getHour(int hour, String date, String dateFormat,
                                 String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 获取当前日期时间之前或者之后某一分钟的日期
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param minute 与当前分钟对比，负数为之前，正数为之后，当前为0
     * @return Date 日期
     */
    public static Date getMinute(int minute){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 获取当前日期时间之前或者之后某一分钟的日期
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param minute 与当前分钟对比，负数为之前，正数为之后，当前为0
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getMinute(int minute, String format){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一分钟的日期
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param minute 与当前分钟对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date getMinute(int minute,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一分钟的日期
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param minute 与当前分钟对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException
     */
    public static Date getMinute(int minute, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一分钟的日期
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param minute 与当前分钟对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getMinute(int minute, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一分钟的日期
     * @author eric
     * @date 2017年6月2日上午11:41:17
     * @param minute 与当前分钟对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException
     */
    public static String getMinute(int minute, String date, String dateFormat,
                                   String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.add(Calendar.MINUTE, minute);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 获取当前日期时间之前或者之后某一秒钟的日期
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param second 与当前秒钟对比，负数为之前，正数为之后，当前为0
     * @return Date 日期
     */
    public static Date getSecond(int second){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 获取当前日期时间之前或者之后某一秒钟的日期
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param second 与当前秒钟对比，负数为之前，正数为之后，当前为0
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getSecond(int second, String format){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.SECOND, second);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一秒钟的日期
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param second 与当前秒钟对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date getSecond(int second,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一秒钟的日期
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param second 与当前秒钟对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException
     */
    public static Date getSecond(int second, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 获得指定日期时间之前或者之后某一秒钟的日期
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param second 与当前秒钟对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getSecond(int second, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获得指定日期时间之前或者之后某一秒钟的日期
     * @author eric
     * @date 2017年6月2日上午11:42:06
     * @param second 与当前秒钟对比，负数为之前，正数为之后，当前为0
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException
     */
    public static String getSecond(int second, String date, String dateFormat,
                                   String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.add(Calendar.SECOND, second);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 通过改变年、月、日、时、分、秒，可将当前时间变更为其他时间
     * @author eric
     * @date 2017年6月1日下午3:32:08
     * @param year 与当前年对比，负数为之前，正数为之后，当前为0
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param day 与当前日对比，负数为之前，正数为之后，当前为0
     * @param Hour 与当前时钟对比，负数为之前，正数为之后，当前为0
     * @param minute 与当前分钟对比，负数为之前，正数为之后，当前为0
     * @param second 与当前秒钟对比，负数为之前，正数为之后，当前为0
     * @return Date 日期
     */
    public static Date getDate(int year, int month, int day, int Hour, int minute, int second){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);
        calendar.add(Calendar.HOUR_OF_DAY, Hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 通过改变年、月、日、时、分、秒，可将当前时间变更为其他时间
     * @author eric
     * @date 2017年6月1日下午3:42:17
     * @param year 与当前年对比，负数为之前，正数为之后，当前为0
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param day 与当前日对比，负数为之前，正数为之后，当前为0
     * @param Hour 与当前时钟对比，负数为之前，正数为之后，当前为0
     * @param minute 与当前分钟对比，负数为之前，正数为之后，当前为0
     * @param second 与当前秒钟对比，负数为之前，正数为之后，当前为0
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getDate(int year, int month, int day, int Hour,
                                 int minute, int second, String format){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);
        calendar.add(Calendar.HOUR_OF_DAY, Hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return getDate(format, calendar.getTime());
    }

    /**
     * 通过改变年、月、日、时、分、秒，可将指定时间变更为其他时间
     * @author eric
     * @date 2017年6月1日下午3:40:00
     * @param date 指定日期时间
     * @param year 与指定年对比，负数为之前，正数为之后，当前为0
     * @param month 与指定月对比，负数为之前，正数为之后，当前为0
     * @param day 与指定日对比，负数为之前，正数为之后，当前为0
     * @param Hour 与指定时钟对比，负数为之前，正数为之后，当前为0
     * @param minute 与指定分钟对比，负数为之前，正数为之后，当前为0
     * @param second 与指定秒钟对比，负数为之前，正数为之后，当前为0
     * @return Date 日期
     */
    public static Date getDate(Date date, int year, int month, int day, int Hour,
                               int minute, int second){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);
        calendar.add(Calendar.HOUR_OF_DAY, Hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 通过改变年、月、日、时、分、秒，可将指定时间变更为其他时间
     * @author eric
     * @date 2017年6月1日下午3:45:55
     * @param date 指定日期时间
     * @param year 与指定年对比，负数为之前，正数为之后，当前为0
     * @param month 与指定月对比，负数为之前，正数为之后，当前为0
     * @param day 与指定日对比，负数为之前，正数为之后，当前为0
     * @param Hour 与指定时钟对比，负数为之前，正数为之后，当前为0
     * @param minute 与指定分钟对比，负数为之前，正数为之后，当前为0
     * @param second 与指定秒钟对比，负数为之前，正数为之后，当前为0
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException 格式化错误
     */
    public static Date getDate(String date, int year, int month, int day, int Hour,
                               int minute, int second, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);
        calendar.add(Calendar.HOUR_OF_DAY, Hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 通过改变年、月、日、时、分、秒，可将指定时间变更为其他时间
     * @author eric
     * @date 2017年6月1日下午3:40:29
     * @param date 指定日期时间
     * @param year 与指定年对比，负数为之前，正数为之后，当前为0
     * @param month 与指定月对比，负数为之前，正数为之后，当前为0
     * @param day 与指定日对比，负数为之前，正数为之后，当前为0
     * @param Hour 与指定时钟对比，负数为之前，正数为之后，当前为0
     * @param minute 与指定分钟对比，负数为之前，正数为之后，当前为0
     * @param second 与指定秒钟对比，负数为之前，正数为之后，当前为0
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getDate(Date date, int year, int month, int day, int Hour,
                                 int minute, int second, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);
        calendar.add(Calendar.HOUR_OF_DAY, Hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return getDate(format, calendar.getTime());
    }

    /**
     * 通过改变年、月、日、时、分、秒，可将指定时间变更为其他时间
     * @author eric
     * @date 2017年6月1日下午3:45:55
     * @param date 指定日期时间
     * @param year 与指定年对比，负数为之前，正数为之后，当前为0
     * @param month 与指定月对比，负数为之前，正数为之后，当前为0
     * @param day 与指定日对比，负数为之前，正数为之后，当前为0
     * @param Hour 与指定时钟对比，负数为之前，正数为之后，当前为0
     * @param minute 与指定分钟对比，负数为之前，正数为之后，当前为0
     * @param second 与指定秒钟对比，负数为之前，正数为之后，当前为0
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException 格式化错误
     */
    public static String getDate(String date, int year, int month, int day, int Hour,
                                 int minute, int second, String dateFormat, String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);
        calendar.add(Calendar.HOUR_OF_DAY, Hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 设置当前日期时间的年，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param year 设置年，从公园1年开始计算
     * @return Date 日期
     */
    public static Date setYear(int year){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 设置当前日期时间的年，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param year 设置年，从公园1年开始计算
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setYear(int year, String format){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的年，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param year 设置年，从公园1年开始计算
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date setYear(int year,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的年，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param year 设置年，从公园1年开始计算
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException
     */
    public static Date setYear(int year, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的年，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param year 设置年，从公园1年开始计算
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setYear(int year, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, year);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的年，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param year 设置年，从公园1年开始计算
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException
     */
    public static String setYear(int year, String date, String dateFormat,
                                 String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.set(Calendar.YEAR, year);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 设置当前日期时间的月，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @return Date 日期
     */
    public static Date setMonth(int month){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 设置当前日期时间的月，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setMonth(int month, String format){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的月，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date setMonth(int month,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的月，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException
     */
    public static Date setMonth(int month, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.set(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的月，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setMonth(int month, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, month);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的月，然后返回日期时间
     * @author eric
     * @date 2017年6月2日下午12:00:02
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException
     */
    public static String setMonth(int month, String date, String dateFormat,
                                  String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.set(Calendar.MONTH, month);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 设置当前日期时间的日，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @return Date 日期
     */
    public static Date setDay(int day){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 设置当前日期时间的日，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setDay(int day, String format){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的日，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date setDay(int day,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的日，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException 格式化错误
     */
    public static Date setDay(int day, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.set(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的日，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setDay(int day, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, day);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的日，然后返回日期时间
     * @author eric
     * @date 2017年6月2日下午12:02:45
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException
     */
    public static String setDay(int day, String date, String dateFormat,
                                String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.set(Calendar.DATE, day);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 设置当前日期时间的小时，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param hour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @return Date 日期
     */
    public static Date setHour(int hour){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 设置当前日期时间的小时，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param hour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setHour(int hour, String format){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的小时，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param hour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date setHour(int hour,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的小时，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param hour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException 格式化错误
     */
    public static Date setHour(int hour, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的小时，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param hour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setHour(int hour, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的小时，然后返回日期时间
     * @author eric
     * @date 2017年6月2日下午12:04:56
     * @param hour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException 格式化错误
     */
    public static String setHour(int hour, String date, String dateFormat,
                                 String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 设置当前日期时间的分钟，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @return Date 日期
     */
    public static Date setMinute(int minute){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 设置当前日期时间的分钟，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setMinute(int minute, String format){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.MINUTE, minute);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的分钟，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date setMinute(int minute,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的分钟，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException
     */
    public static Date setMinute(int minute, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.set(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的分钟，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setMinute(int minute, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, minute);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的分钟，然后返回日期时间
     * @author eric
     * @date 2017年6月2日下午12:07:48
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException
     */
    public static String setMinute(int minute, String date, String dateFormat,
                                   String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.set(Calendar.MINUTE, minute);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 设置当前日期时间的秒，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:31:45
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @return Date 日期
     */
    public static Date setSecond(int second){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 设置当前日期时间的秒，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:37:40
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setSecond(int second, String format){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.SECOND, second);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的秒，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:38:19
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @param date 指定日期
     * @return Date 日期
     */
    public static Date setSecond(int second,Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的秒，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:41:28
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException
     */
    public static Date setSecond(int second, String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 设置指定日期时间的秒，然后返回日期时间
     * @author eric
     * @date 2017年6月1日下午2:39:42
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @param date 指定日期
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setSecond(int second, Date date, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, second);
        return getDate(format, calendar.getTime());
    }

    /**
     * 设置指定日期时间的秒，然后返回日期时间
     * @author eric
     * @date 2017年6月2日下午12:10:23
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @param date 指定日期
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException
     */
    public static String setSecond(int second, String date, String dateFormat,
                                   String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.set(Calendar.SECOND, second);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 通过设置年、月、日、时、分、秒，可将当前时间变更为其他时间
     * @author eric
     * @date 2017年6月1日下午3:32:08
     * @param year 设置年，从公园1年开始计算
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param hour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @return Date 日期
     */
    public static Date setDate(int year, int month, int day, int Hour, int minute, int second){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 通过设置年、月、日、时、分、秒，可将当前时间变更为其他时间
     * @author eric
     * @date 2017年6月1日下午3:42:17
     * @param year 设置年，从公园1年开始计算
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param hour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     */
    public static String setDate(int year, int month, int day, int Hour,
                                 int minute, int second, String format){
        calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, day);
        calendar.add(Calendar.HOUR_OF_DAY, Hour);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, second);
        return getDate(format, calendar.getTime());
    }

    /**
     * 通过设置年、月、日、时、分、秒，可将指定时间变更为其他时间
     * @author eric
     * @date 2017年6月1日下午3:40:00
     * @param date 指定日期时间
     * @param year 设置年，从公园1年开始计算
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param hour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @return String 日期
     */
    public static Date setDate(Date date, int year, int month, int day, int Hour,
                               int minute, int second){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 通过设置年、月、日、时、分、秒，可将指定时间变更为其他时间
     * @author eric
     * @date 2017年6月1日下午3:45:55
     * @param date 指定日期时间
     * @param year 设置年，从公园1年开始计算
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param hour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return Date 日期
     * @throws ParseException 格式化错误
     */
    public static Date setDate(String date, int year, int month, int day, int Hour,
                               int minute, int second, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 通过设置年、月、日、时、分、秒，可将指定时间变更为其他时间
     * @author eric
     * @date 2017年6月1日下午3:40:29
     * @param date 指定日期时间
     * @param year 设置年，从公园1年开始计算
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param hour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String setDate(Date date, int year, int month, int day, int Hour,
                                 int minute, int second, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return getDate(format, calendar.getTime());
    }

    /**
     * 通过设置年、月、日、时、分、秒，可将指定时间变更为其他时间
     * @author eric
     * @date 2017年6月2日下午12:11:37
     * @param date 指定日期时间
     * @param year 设置年，从公园1年开始计算
     * @param month 设置月，0为1月以此类推，负数为之前年的月，12为之后年的1月，以此类推
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @paramhour 设置小时，0为0时以此类推，负数为之前日的小时，24为之后日的0时，以此类推
     * @param minute 设置分钟，0为0分以此类推，负数为之前小时的分钟，60为之后小时的0分，以此类推
     * @param second 设置秒钟，0为0秒以此类推，负数为之前分钟的秒，60为之后分钟的0秒，以此类推
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException 格式化错误
     */
    public static String setDate(String date, int year, int month, int day, int Hour,
                                 int minute, int second, String dateFormat, String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 获取当前日期之前或者之后某月某一天
     * @author eric
     * @date 2017年6月2日上午9:29:41
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @return Date 日期
     */
    public static Date getMonthDay(int month, int day){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获取当前日期之前或者之后某月某一天
     * @author eric
     * @date 2017年6月2日上午9:30:47
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getMonthDay(int month, int day, String format){
        calendar=Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.add(Calendar.MONTH, month);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获取指定日期之前或者之后某月某一天
     * @author eric
     * @date 2017年6月2日上午9:42:48
     * @param date 指定日期
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @return Date 日期
     */
    public static Date getMonthDay(Date date, int month, int day){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获取指定日期之前或者之后某月第一天
     * @author eric
     * @date 2017年6月1日下午1:45:56
     * @param date 指定日期
     * @param month 与指定月对比，负数为之前，正数为之后，当前为0
     * @return Date 日期
     * @throws ParseException 格式化错误
     */
    public static Date getMonthDay(String date, int month, int day,
                                   String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获取指定日期之前或者之后某月某一天
     * @author eric
     * @date 2017年6月2日上午9:34:33
     * @param date 指定日期
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     */
    public static String getMonthDay(Date date, int month, int day, String format){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.add(Calendar.MONTH, month);
        return getDate(format, calendar.getTime());
    }

    /**
     * 获取指定日期之前或者之后某月某一天
     * @author eric
     * @date 2017年6月2日上午9:36:47
     * @param date 指定日期
     * @param month 与当前月对比，负数为之前，正数为之后，当前为0
     * @param day 设置日，1为1日以此类推，从0开始到负数为之前月的日，超过当前月天数为之后月的日，以此类推
     * @param dateFormat 传入字符串日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @param returnFormat 返回字符串参数日期的格式化，需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return String 日期
     * @throws ParseException 格式化错误
     */
    public static String getMonthDay(String date, int month, int day,
                                     String dateFormat, String returnFormat) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(dateFormat, date));
        calendar.set(Calendar.DAY_OF_MONTH,day);
        calendar.add(Calendar.MONTH, month);
        return getDate(returnFormat, calendar.getTime());
    }

    /**
     * 获取实际最大年
     * @author eric
     * @date 2017年6月2日下午1:06:50
     * @return int 最大年
     */
    public static int getActualMaxYear(){
        calendar=Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.YEAR);
    }

    /**
     * 获取实际最小年
     * @author eric
     * @date 2017年6月2日下午1:07:48
     * @return int 最小年
     */
    public static int getActualMinYear(){
        calendar=Calendar.getInstance();
        return calendar.getActualMinimum(Calendar.YEAR);
    }

    /**
     * 获取当前年实际最大月
     * @author eric
     * @date 2017年6月2日下午1:09:08
     * @return int 最大月
     */
    public static int getActualMaxMonth(){
        calendar=Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.MONTH)+1;
    }

    /**
     * 获取当前年实际最小月
     * @author eric
     * @date 2017年6月2日下午1:09:31
     * @return int 最小月
     */
    public static int getActualMinMonth(){
        calendar=Calendar.getInstance();
        return calendar.getActualMinimum(Calendar.MONTH)+1;
    }

    /**
     * 获取指定年实际最大月
     * @author eric
     * @date 2017年6月2日下午1:13:33
     * @param date 指定时间
     * @return int 最大月
     */
    public static int getActualMaxMonth(Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.MONTH)+1;
    }

    /**
     * 获取指定年实际最小月
     * @author eric
     * @date 2017年6月2日下午1:13:41
     * @param date 指定时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 最小月
     * @throws ParseException 格式化错误
     */
    public static int getActualMinMonth(String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.getActualMinimum(Calendar.MONTH)+1;
    }

    /**
     * 获取当前日实际最大时
     * @author eric
     * @date 2017年6月2日下午1:15:03
     * @return int 最大日
     */
    public static int getActualMaxDay(){
        calendar=Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取当前月实际最小日
     * @author eric
     * @date 2017年6月2日下午1:15:42
     * @return int 最小日
     */
    public static int getActualMinDay(){
        calendar=Calendar.getInstance();
        return calendar.getActualMinimum(Calendar.DATE);
    }

    /**
     * 获取指定月实际最大日
     * @author eric
     * @date 2017年6月2日下午1:15:51
     * @param date 指定时间
     * @return int 最大日
     */
    public static int getActualMaxDay(Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取指定月实际最小日
     * @author eric
     * @date 2017年6月2日下午1:16:01
     * @param date 指定时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 最小日
     * @throws ParseException 格式化错误
     */
    public static int getActualMinDay(String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.getActualMinimum(Calendar.DATE);
    }

    /**
     * 获取当前日实际最大时
     * @author eric
     * @date 2017年6月2日下午1:30:18
     * @return int 最大时
     */
    public static int getActualMaxHour(){
        calendar=Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前日实际最小时
     * @author eric
     * @date 2017年6月2日下午1:30:29
     * @return int 最小时
     */
    public static int getActualMinHour(){
        calendar=Calendar.getInstance();
        return calendar.getActualMinimum(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定日实际最大时
     * @author eric
     * @date 2017年6月2日下午1:30:37
     * @param date 指定时间
     * @return int 最大时
     */
    public static int getActualMaxHour(Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定日实际最小时
     * @author eric
     * @date 2017年6月2日下午1:30:45
     * @param date 指定时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 最小时
     * @throws ParseException 格式化错误
     */
    public static int getActualMinHour(String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.getActualMinimum(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前时实际最大分
     * @author eric
     * @date 2017年6月2日下午1:15:03
     * @return int 最大分
     */
    public static int getActualMaxMinute(){
        calendar=Calendar.getInstance();
        return calendar.getActualMaximum(Calendar.MINUTE);
    }

    /**
     * 获取当前时实际最小分
     * @author eric
     * @date 2017年6月2日下午1:15:42
     * @return int 最小分
     */
    public static int getActualMinMinute(){
        calendar=Calendar.getInstance();
        return calendar.getActualMinimum(Calendar.MINUTE);
    }

    /**
     * 获取指定时实际最大分
     * @author eric
     * @date 2017年6月2日下午1:15:51
     * @param date 指定时间
     * @return int 最大分
     */
    public static int getActualMaxMinute(Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.MINUTE);
    }

    /**
     * 获取指定时实际最小分
     * @author eric
     * @date 2017年6月2日下午1:16:01
     * @param date 指定时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 最小分
     * @throws ParseException 格式化错误
     */
    public static int getActualMinMinute(String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.getActualMinimum(Calendar.MINUTE);
    }

    /**
     * 获得当前年周数量
     * @author eric
     * @date 2017年6月2日下午3:47:51
     * @return int 周数量
     */
    public static int getWeeksInWeekYear(){
        calendar=Calendar.getInstance();
        return calendar.getWeeksInWeekYear();
    }

    /**
     * 获得指定年周数量
     * @author eric
     * @date 2017年6月2日下午3:48:58
     * @param date 指定时间
     * @return int 周数量
     */
    public static int getWeeksInWeekYear(Date date){
        calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getWeeksInWeekYear();
    }

    /**
     * 获得指定年周数量
     * @author eric
     * @date 2017年6月2日下午3:50:27
     * @param date 指定时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int 周数量
     * @throws ParseException 格式化错误
     */
    public static int getWeeksInWeekYear(String date, String format) throws ParseException{
        calendar=Calendar.getInstance();
        calendar.setTime(getDate(format, date));
        return calendar.getWeeksInWeekYear();
    }

    /**
     * 判断时间，beforeDate小于afterDate
     * @author eric
     * @date 2017年6月2日下午2:04:45
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @return boolean true：beforeDate在afterDate之前，false：beforeDate在afterDate之后
     */
    public static boolean before(Date beforeDate, Date afterDate){
        if(beforeDate==null){
            return true;
        }
        if(afterDate==null){
            return false;
        }
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        afterCalendar.setTime(afterDate);
        return beforeCalendar.before(afterCalendar);
    }

    /**
     * 判断时间，beforeDate小于afterDate
     * @author eric
     * @date 2017年6月2日下午2:08:49
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return boolean true：beforeDate在afterDate之前，false：beforeDate在afterDate之后
     * @throws ParseException 格式化错误
     */
    public static boolean before(Date beforeDate, String afterDate,
                                 String format) throws ParseException{
        if(beforeDate==null){
            return true;
        }
        if(afterDate==null||"".equals(afterDate)){
            return false;
        }
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        try {
            afterCalendar.setTime(getDate(format, afterDate));
        } catch (Exception e) {
            return false;
        }
        return beforeCalendar.before(afterCalendar);
    }

    /**
     * 判断时间，beforeDate小于afterDate
     * @author eric
     * @date 2017年6月2日下午2:09:57
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return boolean true：beforeDate在afterDate之前，false：beforeDate在afterDate之后
     * @throws ParseException 格式化错误
     */
    public static boolean before(String beforeDate, Date afterDate,
                                 String format) throws ParseException{
        if(beforeDate==null||"".equals(beforeDate)){
            return true;
        }
        if(afterDate==null){
            return false;
        }
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        try {
            beforeCalendar.setTime(getDate(format, beforeDate));
        } catch (Exception e) {
            return true;
        }
        afterCalendar.setTime(afterDate);
        return beforeCalendar.before(afterCalendar);
    }

    /**
     * 判断时间，beforeDate小于afterDate
     * @author eric
     * @date 2017年6月2日下午2:11:25
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return boolean true：beforeDate在afterDate之前，false：beforeDate在afterDate之后
     * @throws ParseException 格式化错误
     */
    public static boolean before(String beforeDate, String afterDate,
                                 String format) throws ParseException{
        if(beforeDate==null||"".equals(beforeDate)){
            return true;
        }
        if(afterDate==null||"".equals(afterDate)){
            return false;
        }
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        try {
            beforeCalendar.setTime(getDate(format, beforeDate));
        } catch (Exception e) {
            return true;
        }
        try {
            afterCalendar.setTime(getDate(format, afterDate));
        } catch (Exception e) {
            return false;
        }
        return beforeCalendar.before(afterCalendar);
    }

    /**
     * 判断时间，afterDate小于beforeDate
     * @author eric
     * @date 2017年6月2日下午1:59:38
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @return boolean true:beforeDate在afterDate之后，false：beforeDate在afterDate之前
     */
    public static boolean after(Date beforeDate, Date afterDate){
        if(beforeDate==null){
            return false;
        }
        if(afterDate==null){
            return true;
        }
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        afterCalendar.setTime(afterDate);
        return beforeCalendar.after(afterCalendar);
    }

    /**
     * 判断时间，afterDate小于beforeDate
     * @author eric
     * @date 2017年6月2日下午2:03:23
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return boolean true:beforeDate在afterDate之后，false：beforeDate在afterDate之前
     * @throws ParseException 格式化错误
     */
    public static boolean after(Date beforeDate, String afterDate,
                                String format) throws ParseException{
        if(beforeDate==null){
            return false;
        }
        if(afterDate==null||"".equals(afterDate)){
            return true;
        }
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        try {
            afterCalendar.setTime(getDate(format, afterDate));
        } catch (Exception e) {
            return true;
        }
        return beforeCalendar.after(afterCalendar);
    }

    /**
     * 判断时间，afterDate小于beforeDate
     * @author eric
     * @date 2017年6月2日下午2:01:24
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return boolean true:beforeDate在afterDate之后，false：beforeDate在afterDate之前
     * @throws ParseException 格式化错误
     */
    public static boolean after(String beforeDate, Date afterDate,
                                String format) throws ParseException{
        if(beforeDate==null||"".equals(beforeDate)){
            return false;
        }
        if(afterDate==null){
            return true;
        }
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        try {
            beforeCalendar.setTime(getDate(format, beforeDate));
        } catch (Exception e) {
            return false;
        }
        afterCalendar.setTime(afterDate);
        return beforeCalendar.after(afterCalendar);
    }

    /**
     * 判断时间，afterDate小于beforeDate
     * @author eric
     * @date 2017年6月2日下午2:03:48
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return boolean true:beforeDate在afterDate之后，false：beforeDate在afterDate之前
     * @throws ParseException 格式化错误
     */
    public static boolean after(String beforeDate, String afterDate,
                                String format) throws ParseException{
        if(beforeDate==null||"".equals(beforeDate)){
            return false;
        }
        if(afterDate==null||"".equals(afterDate)){
            return true;
        }
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        try {
            beforeCalendar.setTime(getDate(format, beforeDate));
        } catch (Exception e) {
            return false;
        }
        try {
            afterCalendar.setTime(getDate(format, afterDate));
        } catch (Exception e) {
            return true;
        }
        return beforeCalendar.after(afterCalendar);
    }

    /**
     * 判断时间，beforeDate与afterDate的比较关系
     * @author eric
     * @date 2017年6月2日下午2:19:57
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @return int -1:beforeDate在afterDate之后
     *              0:beforeDate与afterDate相等
     *              1:beforeDate在afterDate之前
     */
    public static int compareTo(Date beforeDate, Date afterDate){
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        afterCalendar.setTime(afterDate);
        return beforeCalendar.compareTo(afterCalendar);
    }

    /**
     * 判断时间，beforeDate与afterDate的比较关系
     * @author eric
     * @date 2017年6月2日下午2:22:11
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int -1:beforeDate在afterDate之后
     *              0:beforeDate与afterDate相等
     *              1:beforeDate在afterDate之前
     * @throws ParseException 格式化错误
     */
    public static int compareTo(Date beforeDate, String afterDate,
                                String format) throws ParseException{
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        afterCalendar.setTime(getDate(format, afterDate));
        return beforeCalendar.compareTo(afterCalendar);
    }

    /**
     * 判断时间，beforeDate与afterDate的比较关系
     * @author eric
     * @date 2017年6月2日下午2:26:06
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int -1:beforeDate在afterDate之后
     *              0:beforeDate与afterDate相等
     *              1:beforeDate在afterDate之前
     * @throws ParseException 格式化错误
     */
    public static int compareTo(String beforeDate, Date afterDate,
                                String format) throws ParseException{
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(getDate(format, beforeDate));
        afterCalendar.setTime(afterDate);
        return beforeCalendar.compareTo(afterCalendar);
    }

    /**
     * 判断时间，beforeDate与afterDate的比较关系
     * @author eric
     * @date 2017年6月2日下午2:27:11
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @param format 需要得到的字符串日期时间格式 如yyyy-MM-dd HH:mm:ss
     * @return int -1:beforeDate在afterDate之后
     *              0:beforeDate与afterDate相等
     *              1:beforeDate在afterDate之前
     * @throws ParseException 格式化错误
     */
    public static int compareTo(String beforeDate, String afterDate,
                                String format) throws ParseException{
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(getDate(format, beforeDate));
        afterCalendar.setTime(getDate(format, afterDate));
        return beforeCalendar.compareTo(afterCalendar);
    }

    /**
     * 判断时间，beforeDate与afterDate的比较关系
     * @author eric
     * @date 2017年6月2日下午2:34:13
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @return boolean true:beforeDate等于afterDate，false：beforeDate不等于afterDate
     */
    public static boolean equals(Date beforeDate, Date afterDate){
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        afterCalendar.setTime(afterDate);
        return beforeCalendar.equals(afterCalendar);
    }

    /**
     * 判断时间，beforeDate与afterDate的比较关系
     * @author eric
     * @date 2017年6月2日下午2:35:32
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @return boolean true:beforeDate等于afterDate，false：beforeDate不等于afterDate
     */
    public static boolean equals(Date beforeDate, String afterDate,
                                 String format) throws ParseException{
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(beforeDate);
        afterCalendar.setTime(getDate(format, afterDate));
        return beforeCalendar.equals(afterCalendar);
    }

    /**
     * 判断时间，beforeDate与afterDate的比较关系
     * @author eric
     * @date 2017年6月2日下午2:36:04
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @return boolean true:beforeDate等于afterDate，false：beforeDate不等于afterDate
     */
    public static boolean equals(String beforeDate, Date afterDate,
                                 String format) throws ParseException{
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(getDate(format, beforeDate));
        afterCalendar.setTime(afterDate);
        return beforeCalendar.equals(afterCalendar);
    }

    /**
     * 判断时间，beforeDate与afterDate的比较关系
     * @author eric
     * @date 2017年6月2日下午2:36:44
     * @param beforeDate 之前时间
     * @param afterDate 之后时间
     * @return boolean true:beforeDate等于afterDate，false：beforeDate不等于afterDate
     */
    public static boolean equals(String beforeDate, String afterDate,
                                 String format) throws ParseException{
        Calendar beforeCalendar = Calendar.getInstance();
        Calendar afterCalendar = Calendar.getInstance();
        beforeCalendar.setTime(getDate(format, beforeDate));
        afterCalendar.setTime(getDate(format, afterDate));
        return beforeCalendar.equals(afterCalendar);
    }

//  calendar.getCalendarType();
//  calendar.getDisplayName(n, n, null);
//  calendar.getDisplayNames(n, n, null);
//  calendar.getFirstDayOfWeek();--
//  calendar.getGreatestMinimum(n);
//  calendar.getLeastMaximum(n);
//  calendar.getMaximum(n);
//  calendar.getMinimalDaysInFirstWeek();
//  calendar.getMinimum(n);
//  calendar.getTime();
//  calendar.getTimeInMillis();
//  calendar.getTimeZone();
//  calendar.getWeekYear();
//  calendar.getAvailableCalendarTypes();
//  calendar.getAvailableLocales();
//  calendar.hashCode();
//  calendar.isLenient();
//  calendar.isSet(n);
//  calendar.isWeekDateSupported();
//  calendar.notify();
//  calendar.notifyAll();
//  calendar.roll(n, up);
//  calendar.roll(n, n);
//  calendar.set(n, n);
//  calendar.set(n, n, n);
//  calendar.set(n, n, n, n, n);
//  calendar.set(n, n, n, n, n, n);
//  calendar.setFirstDayOfWeek(n);
//  calendar.setLenient(lenient);
//  calendar.setMinimalDaysInFirstWeek(n);
//  calendar.setTime(date);
//  calendar.setTimeInMillis(n);
//  calendar.setTimeZone(value);
//  calendar.setWeekDate(n, n, n);
//  calendar.toInstant();
//  calendar.toString();
}
