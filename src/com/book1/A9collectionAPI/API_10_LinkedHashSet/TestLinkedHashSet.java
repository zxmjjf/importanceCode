package com.book1.A9collectionAPI.API_10_LinkedHashSet;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 * @author book1
 * @version 2019-09-17
 */
public class TestLinkedHashSet {
    private static LinkedHashSet<Integer> linkedHashSet;
    private static HashSet<Integer> hashSet;

    public static void main(String[] args) {
        inIt();
    }

    private static void inIt(){
        linkedHashSet = new LinkedHashSet<>();
        hashSet = new HashSet<>();
        int cur;
        Random random = new Random();
        for (int i = 0; i < 10; ++i) {
            cur = random.nextInt(100);
            System.out.print(cur + "\t");
            linkedHashSet.add(cur);
            hashSet.add(cur);
        }
        System.out.println();
        System.out.println("linkedHashSet = " + linkedHashSet);
        linkedHashSet.forEach(v -> System.out.print(v + "\t"));
        System.out.println();
        System.out.println("HashSet = " + hashSet);
    }
}
