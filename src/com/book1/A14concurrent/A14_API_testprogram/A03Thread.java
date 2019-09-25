package com.book1.A14concurrent.A14_API_testprogram;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 测试改变线程状态的常用方法
 */
public class A03Thread {
    public static Thread thread = new Thread();
    public static Thread thread0 = new Thread();
    public static int counter = 0;
    public static void main(String[] args) {
        testSleep();
        //testJoin();

    }

    /**
     * sleep() test
     */
    public static void testSleep(){
        /*线程1 */
        Thread thread1 = new Thread(() ->{
            try {
                while (++counter < 6) {
                    System.out.println("线程1\t线程2 = " + thread.getState());
                    Thread.currentThread().sleep(3000);
                }
            } catch (InterruptedException e) {
                System.out.println("sleep() + intrrupted == IntrruptedExection");
            }
        });

        /* 线程2*/
        Thread thread2 = new Thread(() -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                int anInt = 0;
                @Override
                public void run() {
                    if (++anInt < 10) {
                        /*System.out.println("线程2");
                        System.out.println(thread1.getState());
                        System.out.println(Thread.currentThread().getState());
                        Thread.yield();
                        System.out.println(thread1.getState());*/
                        try {
                            System.out.println("线程2\t线程1 = " + thread1.getState());
                            thread1.join();
                        } catch (InterruptedException e) {
                            System.out.println("异常！！");
                        }
                        /*thread1.interrupt();*/
                    } else {
                        System.exit(0);
                    }
                }
            }, 1000, 1000);
        });

        thread = thread2;
        /*启动线程*/
        thread1.start();
        thread2.start();
    }

    /**
     * join() test
     */
    public static void testJoin(){
        /*线程1 */
        Thread thread1 = new Thread(() ->{
            try {
                while (true) {
                    System.out.println("在线程1中，线程2的状态为" + thread.getState());
                    System.out.println("在线程1中，线程3的状态为" + thread0.getState());
                    Thread.currentThread().join();
                }
            } catch (InterruptedException e) {
                System.out.println("sleep() + intrrupted == IntrruptedExection");
            }
            /*Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                int anInt = 0; //控制线程任务的循环次数
                @Override
                public void run() {
                    if (++anInt < 6) {
                        try {
                            System.out.println("在线程1中，线程2的状态为" + thread.getState());
                            System.out.println("在线程1中，线程3的状态为" + thread0.getState());
                            Thread.currentThread().join();
                        } catch (InterruptedException e) {
                            System.out.println("线程1异常");
                        }

                    } else {
                        System.exit(0);
                    }
                }
            }, 1000, 300);*/
        });

        /* 线程2*/
        Thread thread2 = new Thread(() -> {
            //Timer timer = new Timer();
            /*timer.schedule(new TimerTask() {
                int anInt = 0; //控制线程任务的循环次数
                @Override
                public void run() {
                    if (++anInt < 20) {
                        System.out.println("在线程2中，线程1的状态为" + thread1.getState());
                        System.out.println("在线程2中，线程3的状态为" + thread0.getState());
                        try {
                            thread1.join();
                        } catch (InterruptedException e) {
                            System.out.println("线程2异常！！");
                        }
                        //thread1.notify();
                        //System.out.println(thread1.getState());
                    } else {
                        System.exit(0);
                    }
                }
            }, 1000, 300);*/
            int aInt = 0;
            while (++aInt < 20) {
                try {
                    System.out.print("线程2.。。");
                    thread1.join();
                } catch (InterruptedException e) {

                }
            }
            System.out.println();

        });
        Thread thread3 = new Thread(() -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                int anInt = 0; //控制线程任务的循环次数
                @Override
                public void run() {
                    if (++anInt < 20) {
                        System.out.println("在线程3中，线程1的状态为" + thread1.getState());
                        System.out.println("在线程3中，线程2的状态为" + thread2.getState());
                        //Thread.yield();
                    } else {
                        System.exit(0);
                    }
                }
            }, 1000, 300);
        });
        /*Thread thread4 = new Thread(() -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                int anInt = 0; //控制线程任务的循环次数
                @Override
                public void run() {
                    if (++anInt < 10) {
                        System.out.println("线程4。。。。");
                        System.out.println(thread1.getState());
                        System.out.println(thread2.getState());
                        System.out.println(thread2.getState());
                        //Thread.currentThread().notifyAll();
                    } else {
                        System.exit(0);
                    }
                }
            }, 1000, 1000);
        });*/

        /*启动线程*/
        thread = thread2;
        thread0 = thread3;
        thread1.start();
        thread2.start();
        thread3.start();
        //thread4.start();*/
    }

    public static void testLongtime(){
        Thread thread1 = new Thread(() ->{
            while (true) {
                System.out.println("线程1中：线程2=" + thread.getState() + "\t线程3 = " + thread0.getState());
            }

        });
        Thread thread2 = new Thread(() ->{
            while (true) {
                System.out.println("线程2中：线程1=" + thread1.getState() + "\t线程3 = " + thread0.getState());
            }

        });
        Thread thread3 = new Thread(() ->{
            while (true) {
                System.out.println("线程3中：线程1=" + thread1.getState() + "\t线程2 = " + thread2.getState());
            }

        });
        thread = thread2;
        thread0 = thread3;
        thread1.start();
        thread2.start();
        thread3.start();
    }


}
