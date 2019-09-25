package com.book1.A14concurrent.A14_API_testprogram;

import java.util.Timer;
import java.util.TimerTask;


public class JoinAddJoin {
    static Thread thread;
    static Thread thread0;
    public static void main(String[] args) {
        testJoin2();
        //testJoin1();
    }
    public static void testJoin1(){
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
        });

        /* 线程2*/
        Thread thread2 = new Thread(() -> {
            int aInt = 0;
            while (++aInt < 20) {
                try {
                    System.out.println("在线程2中，线程1的状态为" + thread1.getState());
                    thread1.join();
                    System.out.println("在线程2中，线程1的状态为" + thread1.getState());
                } catch (InterruptedException e) {
                    System.out.println("线程2异常");
                }
            }
            System.out.println();

        });
        /*线程3*/
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
        /*启动线程*/
        thread = thread2;
        thread0 = thread3;
        thread1.start();
        thread2.start();
        thread3.start();
        //thread4.start();*/
    }
    public static void testJoin2(){
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
        });

        /* 线程2*/
        Thread thread2 = new Thread(() -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
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
            }, 1000, 300);
        });
        /*线程3*/
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
        /*启动线程*/
        thread = thread2;
        thread0 = thread3;
        thread1.start();
        thread2.start();
        thread3.start();
        //thread4.start();*/
    }

}
