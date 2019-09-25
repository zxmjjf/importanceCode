package com.book1.A14concurrent.A14_Knowledge_testprogram;

public class A04ThreadLocal {
    public static int contrast = 0;
    public static final ThreadLocal<String> stringThreadLocal = ThreadLocal.withInitial(() -> new String("book1"));
    public static void main(String[] args) {
        Thread thread1 = new Thread(() ->{
            System.out.println(stringThreadLocal.get() + "\t" + contrast);
            //contrast = 1;
            //stringThreadLocal.set("thread1");
            //Thread.currentThread().interrupt();
            System.out.println(stringThreadLocal.get() + "\t" + contrast);
        });
        Thread thread2 = new Thread(() ->{
            System.out.println(stringThreadLocal.get());
            Thread.currentThread().interrupt();
            contrast = 2;
            stringThreadLocal.set("thread2");
            System.out.println(stringThreadLocal.get() + "\t" + contrast);
        });

        thread1.start();
        thread2.start();
    }
}
