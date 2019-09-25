package com.jjf.A9collectionAPI.API_4_HashSet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Spliterator;

/**
 * @autor jjf
 * @version 1.0.1  2019-08-16
 */
public class HashSetOfMethodTest {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        System.out.println(hashSet.size());
        hashSet.add(6);
        hashSet.add(4);
        hashSet.add(3);
        hashSet.add(1);
        hashSet.add(4);
        hashSet.add(8);
        System.out.println(hashSet.size());
        hashSet.add(0);
        hashSet.add(22);
        hashSet.add(16);
        hashSet.add(17);
        hashSet.add(38);
        for (int i = 30; i < 50; ++i) {
            hashSet.add(i);
        }
        hashSet.add(77);
        hashSet.add(78);

        System.out.println(hashSet.size());
        System.out.println();
        System.out.println(hashSet);

        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "    ");
        }


        System.out.println(hashSet.contains(55));
        System.out.println(hashSet.contains(17));

    }
}
