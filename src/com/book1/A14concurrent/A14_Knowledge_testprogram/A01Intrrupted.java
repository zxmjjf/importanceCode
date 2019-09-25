package com.book1.A14concurrent.A14_Knowledge_testprogram;

import java.util.Timer;
import java.util.TimerTask;

public class A01Intrrupted {
    public static int cur = 0;
    public static int curr = 0;
    public static Thread thread;
    public static void main(String[] args) {
        Thread thread1 = new Thread(() ->{
            while(true){
                //System.out.println(curr++);
                try {
                    System.out.println("在线程1  线程2 = " + thread.getState());
                    //System.exit(0);
                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    System.out.println("异常发生，线程1 exit");
                    System.out.println("线程1异常  线程1 = " + Thread.currentThread().getState());
                }
            }

        }, "thread1");
        Thread thread2 = new Thread(() ->{
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("在线程2 线程1 = " + thread1.getState());
                thread1.interrupt();
                if (cur++ > 7) {
                    System.exit(0);
                }
            }
        }, 1000, 1000);
    }, "thread2");
    thread = thread2;
    thread1.start();
    thread2.start();

    }
}
