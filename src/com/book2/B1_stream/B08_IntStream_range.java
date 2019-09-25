package com.book2.B1_stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class B08_IntStream_range {
    public static void main(String[] args) {
        IntStream intStream = IntStream.range(-5, 15);
        int[] ints = intStream.toArray();
        System.out.println(Arrays.toString(ints));

        intStream = IntStream.rangeClosed(-5, 15);
        ints = intStream.toArray();
        System.out.println(Arrays.toString(ints));
    }
}
