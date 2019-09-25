package com.book1.A14concurrent.A14_API_testprogram;

/**
 * 测试线程抛出未捕获异常
 * @author book1
 * @version 1.0.1
 */
public class A01Thread {
    public static void main(String[] args) {
        Runnable runnable1 = new Runnable() {
            /**
             * 此方法直接抛出异常
             */
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException exception){
                    System.out.println(" thread1线程 InterruptedException ");
                    System.out.println(Thread.interrupted() + "\t 中断标志未设置成功");
                }
                System.out.println("开始抛出异常");
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
                System.out.println("开始抛出异常");
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

        /** 延迟1妙启动线程 */
        try {
            Thread.sleep(1000);
            thread1.start();
            thread2.start();

            /** 两个线程都将处于阻塞 ，此时调用下面方法，将会抛出 InterrupteException异常*/
            System.out.println(thread1.isInterrupted());
            thread1.interrupt();
            thread2.interrupt();
            /** 而上面两个异常被捕获，所以thread1和thread2不会终止，如果没有捕获，则终止*/

            /** 主线程让步 4秒，等其他线程执行完毕 */
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println(" main线程 InterruptedException ");
        }
        System.out.println("main线程 无异常发生");
    }
}



