package com.book2.B1_stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class B09_IntStream_codePoints {
    public static void main(String[] args) {
        String string = "\uD955\uD835\uDD46\uDD001 is the 姜剑锋, 一二三四五abcd";
        IntStream intStream = string.codePoints();
        int[] ints = intStream.toArray();
        System.out.println(Arrays.toString(ints));

        intStream = string.chars();
        ints = intStream.toArray();
        System.out.println(Arrays.toString(ints));
    }
}
