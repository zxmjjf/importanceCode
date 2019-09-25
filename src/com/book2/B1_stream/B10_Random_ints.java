package com.book2.B1_stream;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class B10_Random_ints {
    public static void main(String[] args) {
        IntStream intStream;
        Random random = new Random();
        intStream = random.ints(10, 0, 2);
        int[] ints = intStream.toArray();
        System.out.println(Arrays.toString(ints));
    }
}
