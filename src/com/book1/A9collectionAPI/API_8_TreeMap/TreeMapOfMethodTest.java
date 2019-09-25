package com.book1.A9collectionAPI.API_8_TreeMap;

import java.util.*;

public class TreeMapOfMethodTest {
    static TreeMap<Integer, String> map = new TreeMap<>();
    static HashMap<Integer, String> map1 = new HashMap<>();

    public static void main(String[] args) {
        Random random = new Random();
        int base;
        for (int i = 0; i < 20; ++i) {
            base = random.nextInt(250);
            map.put(base, "book1" + base);
            map1.put(base, "zxm" + base);
        }

        System.out.println(map);
        System.out.println(map1);

        System.out.println(map.get(1));

        Map.Entry<Integer, String> entry = map.ceilingEntry(60);

        System.out.println(entry.toString());
        System.out.println(entry);

        /** forEach() */
        map.forEach((key, value) -> System.out.println(key + "->" + value));

        NavigableSet hashSet = map.descendingKeySet();
        //TreeSet treeSet = (TreeSet)hashSet;
        System.out.println(hashSet);


    }
}
