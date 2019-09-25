package com.book1.A14concurrent.A14_Knowledge_testprogram;

import java.util.Timer;
import java.util.TimerTask;

public class A02Intrrupted extends Thread {
    public static void main(String[] args) {
        A02Intrrupted intrrupted = new A02Intrrupted();
        intrrupted.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int cur = 0;
            @Override
            public void run() {
                System.out.println("main 线程执行中");
                if (++cur  > 5 && cur < 8) {
                    intrrupted.interrupt();
                    System.out.println(intrrupted.isInterrupted());
                }
                if (cur > 15) {
                    System.exit(0);
                }
            }
        }, 1000, 1000);
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("第二个线程执行中");
                interrupt();
                System.out.println(isInterrupted());
            }
        }, 1000, 3000);
    }
}
