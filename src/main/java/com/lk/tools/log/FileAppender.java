package com.lk.tools.log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Writer;

import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;
import org.apache.log4j.spi.ErrorCode;
/**
 * @author luokun
 * @date 2019/4/23 15:12
 */
public class FileAppender extends WriterAppender{
    protected boolean fileAppend = true;

    protected String fileName = null;

    protected String fileNameFormat = null;

    protected boolean bufferedIO = false;

    protected int bufferSize = 8*1024;

    public FileAppender() {
    }

    public FileAppender(Layout layout, String filename, boolean append, boolean bufferedIO,
                        int bufferSize) throws IOException {
        this.layout = layout;
        this.setFile(filename, append, bufferedIO, bufferSize);
    }

    public FileAppender(Layout layout, String filename, boolean append)throws IOException {
        this.layout = layout;
        this.setFile(filename, append, false, bufferSize);
    }

    public FileAppender(Layout layout, String filename) throws IOException {
        this(layout, filename, true);
    }

    public void setFile(String file) {
        String val = file.trim();
        fileNameFormat = val;
    }

    public boolean getAppend() {
        return fileAppend;
    }

    public String getFile() {
        return fileName;
    }

    public void activateOptions() {
        if(fileName != null) {
            try {
                setFile(fileName, fileAppend, bufferedIO, bufferSize);
            }
            catch(java.io.IOException e) {
                errorHandler.error("setFile("+fileName+","+fileAppend+") call failed.",
                        e, ErrorCode.FILE_OPEN_FAILURE);
            }
        } else {
            LogLog.warn("File option not set for appender ["+name+"].");
            LogLog.warn("Are you using FileAppender instead of ConsoleAppender?");
        }
    }

    protected void closeFile() {
        if(this.qw != null) {
            try {
                this.qw.close();
            }
            catch(java.io.IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("Could not close " + qw, e);
            }
        }
    }

    public boolean getBufferedIO() {
        return this.bufferedIO;
    }

    public int getBufferSize() {
        return this.bufferSize;
    }

    public void setAppend(boolean flag) {
        fileAppend = flag;
    }

    public void setBufferedIO(boolean bufferedIO) {
        this.bufferedIO = bufferedIO;
        if(bufferedIO) {
            immediateFlush = false;
        }
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public synchronized void setFile(String fileName, boolean append,
                                     boolean bufferedIO, int bufferSize) throws IOException {
        LogLog.debug("setFile called: "+fileName+", "+append);
        if(bufferedIO) {
            setImmediateFlush(false);
        }
        reset();
        FileOutputStream ostream = null;
        try {
            ostream = new FileOutputStream(fileName, append);
        } catch(FileNotFoundException ex) {
            String parentName = new File(fileName).getParent();
            if (parentName != null) {
                File parentDir = new File(parentName);
                if(!parentDir.exists() && parentDir.mkdirs()) {
                    ostream = new FileOutputStream(fileName, append);
                } else {
                    throw ex;
                }
            } else {
                throw ex;
            }
        }
        Writer fw = createWriter(ostream);
        if(bufferedIO) {
            fw = new BufferedWriter(fw, bufferSize);
        }
        this.setQWForFiles(fw);
        this.fileName = fileName;
        this.fileAppend = append;
        this.bufferedIO = bufferedIO;
        this.bufferSize = bufferSize;
        writeHeader();
        LogLog.debug("setFile ended");
    }

    protected void setQWForFiles(Writer writer) {
        this.qw = new QuietWriter(writer, errorHandler);
    }

    protected void reset() {
        closeFile();
        this.fileName = null;
        super.reset();
    }
}
