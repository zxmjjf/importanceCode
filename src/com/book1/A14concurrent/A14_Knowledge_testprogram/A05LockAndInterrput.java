package com.book1.A14concurrent.A14_Knowledge_testprogram;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

public class A05LockAndInterrput {
    public static Thread thread;
    public static Thread thread0;
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("线程1已经运行");
            function();
        }, "线程1\t");

        Thread thread2 = new Thread(() -> {
            System.out.println("线程2已经运行");
            function();
        }, "线程2\t");

        thread = thread2;
        thread0 = thread1;
        thread1.start();
        thread2.start();


    }


    public static ReentrantLock reentrantLock = new ReentrantLock();
    public static void function(){
        reentrantLock.lock();
        try{
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                int i = 0;
                @Override
                public void run() {
                    if (++i < 5) {
                        System.out.println("在" + Thread.currentThread().getName() +"\t" + thread.getName() +"\t" + thread.getState() +"\t\t"
                        + thread0.getName() +"\t" + thread0.getState());

                        //thread.interrupt();

                        System.out.println("在" + Thread.currentThread().getName() +"\t" + thread.getName() +"\t" + thread.getState() +"\t\t"
                                + thread0.getName() +"\t" + thread0.getState());
                    } else{
                        System.exit(0);
                    }

                }
            }, 1000, 300);

        } finally {
            reentrantLock.unlock();
        }

    }
}
