package com.book1.A7logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.*;

public class MyFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord logRecord) {
        return logRecord.getMessage() == "book1";
    }

    public static void main(String[] args) throws IOException {
        Logger logger =  Logger.getLogger("com.chaptor07.test_log");
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);
        FileHandler fileHandler = new FileHandler("src/com/book1/A7logger/fiter.txt");
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        //fileHandler.setFilter(new MyFilter());

        logger.severe("book1"); // 1
        logger.log(Level.SEVERE, "book1", new FileNotFoundException());

        logger.warning("book1"); // 2
        logger.warning("jjf1");

        logger.info("jjf1");
        logger.info("jjf2");

        logger.config("jjf1");
        logger.config("jjf2");

        logger.fine("book1"); // 3
        logger.fine("book1"); // 4

        logger.finest("book1"); // 5
        logger.finest("jjf1");

        logger.entering("com.book1.A7logger.MyFilter", "func", 6);
        logger.exiting("com.book1.A7logger.MyFilter", "func", func(6));
        System.out.println("完毕!!");

    }

    public static int func(int a){
        System.out.println(a);
        Thread.dumpStack();
        return a + 1;
    }
}
