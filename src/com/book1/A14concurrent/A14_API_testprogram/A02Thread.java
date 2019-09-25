package com.book1.A14concurrent.A14_API_testprogram;

/**
 * 设置自己的未捕获异常处理器
 * @author book1
 * @version 1.0.1
 */
public class A02Thread {
    public static void main(String[] args) {
        Runnable runnable1 = new Runnable() {
            /**
             * 此方法直接抛出异常
             */
            @Override
            public void run() {
                System.out.println("线程1.。。");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException exception){
                    System.out.println(" thread1线程 InterruptedException ");
                }
                if(true){
                    throw new ArrayIndexOutOfBoundsException();
                }
                System.out.println("抛出了数组越界异常");
            }
        };
        Runnable runnable2 = new Runnable() {
            /**
             * 此方法直接抛出异常
             */
            @Override
            public void run() {
                System.out.println("线程2。。。");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception){
                    System.out.println(" thread2线程 InterruptedException ");
                }
                if(true){
                    throw new ClassCastException();
                }
                System.out.println("抛出了数组越界异常");
            }
        };
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        /** 建立自己的异常处理器 */
        Thread.UncaughtExceptionHandler myHandler1 = (t, e) -> System.out.println("调用自己的未捕获异常处理器，未thread1量身定做");
        Thread.UncaughtExceptionHandler myHandler2 = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
                System.out.println(t.toString());
            }
        };
        /** thread1 线程设置自定义的未捕获异常处理器 */
        thread1.setUncaughtExceptionHandler(myHandler1);
        Thread.setDefaultUncaughtExceptionHandler(myHandler2);

        /** 延迟1妙启动线程 */
        try {
            Thread.sleep(1000);
            thread1.start();
            thread2.start();

            /** 主线程让步 4秒，等其他线程执行完毕 */
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println(" main线程 InterruptedException ");
        }
        System.out.println("main线程 无异常发生");

    }
}
