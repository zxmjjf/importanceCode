package com.book1.A14concurrent.codeforbook;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class A02SynchBank {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        System.exit(0);
        Bank1 bank = new Bank1(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Thread thread = new Thread(() ->{
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {

                }
            });

            thread.start();


        }
    }
}

class Bank1{
    private ReentrantLock bankLock = new ReentrantLock(); //add
    private final double[] accounts;
    private int anInt = 3;

    public Bank1(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount) {
        double  newamount = accounts[from];
        bankLock.lock();
        try{
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;

            System.out.printf("%10.2f%10.2f from %d to %d", newamount, amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f", getTotalBalance());
            System.out.println("\t\t" + bankLock.getQueueLength() + "\t" + ++anInt );

        } finally {
            bankLock.unlock();
        }

    }

    public double getTotalBalance() {
        double sum = 0;
        for (double a : accounts) {
            sum += a;
        }
        return  sum;
    }

    public int size(){
        return accounts.length;
    }
}
