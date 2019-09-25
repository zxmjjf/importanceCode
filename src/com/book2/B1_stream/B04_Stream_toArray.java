package com.book2.B1_stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jjf
 * @version 2019-09-22 1.0.1
 * <describe>toArray的用法</describe>
 *
 */
public class B04_Stream_toArray {
    public static void main(String[] args) {
        Stream<Integer> integerStream1 = Stream.generate(() -> new Random().nextInt(100));
        Object[] ints = integerStream1.limit(15).toArray();
        int a = (int)ints[0];
        System.out.println(a);
        System.out.println(Arrays.toString(ints));

        Stream<Integer> integerStream2 = Stream.generate(() -> new Random().nextInt(100));
        Integer[] integers = integerStream2.limit(15).toArray(Integer[]::new);
        System.out.println(Arrays.toString(integers));

        Stream<Integer> integerStream3 = Stream.of(integers);
        int l = integerStream3.limit(15).reduce(-30, Integer::sum);
        System.out.println(l);


    }
}
