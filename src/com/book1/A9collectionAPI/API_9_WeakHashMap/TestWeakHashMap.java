package com.book1.A9collectionAPI.API_9_WeakHashMap;

import java.util.Random;
import java.util.WeakHashMap;

/**
 * <describe>测试弱引用</describe>
 */
public class TestWeakHashMap {
    private static WeakHashMap<MyClass, String> weakHashMap;

    public static void main(String[] args) {
        inIt();
        System.out.println(weakHashMap);

        Thread thread = new Thread(() ->{
            for (int i = 0; i < 10; ++i) {
                System.out.println(weakHashMap);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        thread.start();
    }
    private static void inIt(){
        int cur;
        weakHashMap = new WeakHashMap<>();
        Random random = new Random(500);
        for (int i = 0; i < 10; ++i) {
            cur = random.nextInt(30);
            weakHashMap.put(new MyClass(cur), "A" + cur);
        }
    }
}

class MyClass{
    private int i;
    MyClass(int i){
        this.i = i;
    }

    @Override
    public String toString() {
        return i + "";
    }
}