package com.book1.A9collectionAPI.API_2_ArrayList;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author book1
 * @version 2019-09-17
 */
public class TestArrayList {
    public static void main(String[] args) {
        int cur;
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            cur = random.nextInt(100);
            System.out.print(cur + "  ");
            arrayList.add(cur);
        }
        System.out.println();
        System.out.println(arrayList);
    }
}
