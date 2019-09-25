package com.book2.B1_stream;

import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class B11_Stream_unordered {
    public static void main(String[] args) {
        Random random = new Random(20);
        IntStream intStream = random.ints(20, 0, 10);
        int[] ints = intStream.toArray();
        System.out.println(Arrays.toString(ints));
        System.out.println();

        intStream = IntStream.of(ints);
        intStream.parallel().unordered().limit(10).forEach(System.out::print);
        System.out.println();

        intStream = IntStream.of(ints);
        intStream.parallel().limit(10).forEach(System.out::print);
        System.out.println();

        intStream = IntStream.of(ints);
        intStream.unordered().limit(10).forEach(System.out::print);
        System.out.println();

        intStream = IntStream.of(ints);
        intStream.limit(10).forEach(System.out::print);
        System.out.println();

        intStream = IntStream.of(ints);
        intStream.distinct().forEach(System.out::print);
    }
}
