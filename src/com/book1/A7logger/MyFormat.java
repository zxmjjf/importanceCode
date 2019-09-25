package com.book1.A7logger;

import java.io.IOException;
import java.util.logging.*;

/**
 * 建立自己的格式化器
 * @author book1
 * @version 1.0.1  2019-31-15
 * 描述：如果将处理器的格式化器改为此类定义的格式化器，则对于每一条recode都转化为：record.getLevel() + "：book1\n"
 */
public class MyFormat extends Formatter {
    @Override
    public String format(LogRecord record) {
        return record + "：book1\n";
    }

    public static void main(String[] args) throws Exception, SecurityException {
        Logger logger = Logger.getLogger("com.chaptor07.test_log.MyFormat");

        FileHandler fileHandler = new FileHandler("mylog.log");
        logger.addHandler(fileHandler);
        MyFormat myFormat = new MyFormat();
        //SimpleFormatter simpleFormatter = new SimpleFormatter();
        //fileHandler.setFormatter(simpleFormatter);
        //fileHandler.setFormatter(new XMLFormatter());
        fileHandler.setFormatter(myFormat);

        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);
        //建立自定义的处理器，设置等级和记录器的一致
        Handler handler = new ConsoleHandler();
        //handler.setFormatter(myFormat);
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);

        logger.severe("SEVERE message");
        logger.warning("WARNING message");
        logger.info("INFO message");
        logger.config("CONFING message");
        logger.fine("FINE message");
        //由于下面等级比较低，所以，不会被记录再log.txt文件中
        logger.finer("FINER message");
        logger.finest("FINEST message");


        //记录异常
        try {
            throw new IOException("IOEx");
        } catch (IOException e) {
            logger.log(Level.WARNING, "异常日志", e);
        }
        //throw new IOException();

        //记录方法
        logger.entering("com.chaptorffr.test_log.MyFormat", "main", args);

    }
}
