package com.book1.A14concurrent.A14_Knowledge_testprogram;

public class A03waitStatue {
    private static Thread thread01, thread02;
    private static int counter = 0;
    private static int nooff = 0;
    public static void main(String[] args) {
        A03waitStatue a03waitStatue = new A03waitStatue();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (++counter < 50) {
                    a03waitStatue.faunc();
                }

            }
        });

        Thread thread2 = new Thread(() ->{
            while (++counter < 50) {
                a03waitStatue.faunc();
            }

        });

        thread01 = thread1;
        thread02 = thread2;

        thread1.start();
        thread2.start();
    }
    public  synchronized void faunc(){
        try {
            if (counter < 20) {
                if (Thread.currentThread() == thread01) {
                    System.out.println("线程1调用wait");
                    wait();
                    System.out.println("线程1激活完成");
                } else  {
                    System.out.println("线程2激活线程1\t线程1 = " + thread01.getState());
                    notifyAll();
                }
            } else {
                if (counter < 50) {
                    if (Thread.currentThread() == thread01) {
                        System.out.println("线程1激活线程2\t线程2 = \t" + thread02.getState());
                        notifyAll();
                    } else {
                        System.out.println("线程2调用wait");
                        wait();
                        System.out.println("线程2激活完成");
                    }
                }

            }
        } catch (InterruptedException e) {
            System.out.println("异常");
        }
    }
}
