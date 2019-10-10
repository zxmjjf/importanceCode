package com.book2.B1_stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class B07_Stream_collect {
    public static void main(String[] args) {
        Stream<Integer> integerStream0 = Stream.generate(() -> new Random().nextInt(100));
        Integer[] integers = integerStream0.limit(15).toArray(Integer[]::new);
        System.out.println(Arrays.toString(integers));


        /* stream.collect(groupingBy(v -> v > 50))*/
        System.out.println();
        Stream<Integer> integerStream1 = Stream.of(integers);
        Map<Boolean, List<Integer>> map = integerStream1.collect(groupingBy(v -> v > 50));
        System.out.println(map);


        /* stream.collect(groupingBy(v -> v > 50, maxBy(Comparator.comparing(Function.identity()))));*/
        System.out.println();
        Stream<Integer> integerStream2 = Stream.of(integers);
        Map<Boolean, Optional<Integer>> map1 = integerStream2.collect(groupingBy(v -> v > 50, maxBy(Comparator.comparing(Function.identity()))));
        System.out.println(map1);


        /* stream.collect(partitioningBy(v -> v > 50, toSet()))*/
        System.out.println();
        Stream<Integer> integerStream3 = Stream.of(integers);
        Map<Boolean, Set<Integer>> map2 = integerStream3.collect(partitioningBy(v -> v > 50, toSet()));
        System.out.println(map2);


        /*steam.collect(groupingBy(String::length, mapping(Function.identity(), joining("-"))));*/
        System.out.println();
        Stream<String> integerStream4 = Stream.of("zxm1", "zxm2", "jjf0", "jjf", "zxm4", "aaaaaa", "bbbbbbbb","cc", "dd");
        Map<Integer, String> map3 = integerStream4.collect(groupingBy(String::length, mapping(Function.identity(), joining("-"))));
        System.out.println(map3);


        /* stream.collect(partitioningBy(v -> v > 50, mapping(Object::toString, joining("-"))));*/
        System.out.println();
        Stream<Integer> integerStream5 = Stream.of(integers);
        Map<Boolean, String> map4 = integerStream5.collect(partitioningBy(v -> v > 50, mapping(Object::toString, joining("-"))));
        System.out.println(map4);


        /* stream.collect(groupingBy(v -> v % 5, mapping(Function.identity(), toCollection(TreeSet::new))));*/
        System.out.println();
        Stream<Integer> integerStream6 = Stream.of(integers);
        Map<Integer, TreeSet<String>> map5 = integerStream6.collect(groupingBy(v -> v % 5, mapping(v -> v + "jjf", toCollection(TreeSet::new))));
        System.out.println(map5);
    }

}
