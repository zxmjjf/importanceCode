package com.book1.A9collectionAPI.API_11_LinkedHashMap;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Random;


public class TestLinkedHashMap {
    public static void main(String[] args) {
        Random random = new Random();
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        int[] arrays = new int[20];
        int num = 0;
        for (int i = 0; i < 20; ++i) {
            num = random.nextInt(100);
            arrays[i] = num;
            linkedHashMap.put(num, num);
        }

        System.out.println(Arrays.toString(arrays));
        linkedHashMap.forEach((k, v) -> System.out.print(k + "=" + linkedHashMap.get(k) + "\t"));
        //迭代的顺序是最后访问的顺序
    }
}
