package com.book2.B1_stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class B05_Stream_reduce {
    public static void main(String[] args) {
        Stream<Integer> integerStream1 = Stream.generate(() -> new Random().nextInt(100));
        Integer[] integers = integerStream1.limit(15).toArray(Integer[]::new);
        System.out.println(Arrays.toString(integers));

        //T reduce(T identity,BinaryOperator<T> accumulator)
        Stream<Integer> integerStream2 = Stream.of(integers);
        int l = integerStream2.reduce(0, Integer::sum);
        System.out.println(l);

        //Optional<T> reduce(BinaryOperator<T> accumulator)
        Stream<Integer> integerStream3 = Stream.of(integers);
        Optional<Integer> l1 = integerStream3.reduce((v1, v2) -> v1 - v2);
        System.out.println(l1);

        //<U> U reduce(U identity,BiFunction<U,? super T,U> accumulator,BinaryOperator<U> combiner)
        //这个方法主要在并发的时候提升性能。
        Stream<Integer> integerStream4 = Stream.of(integers);
        int l2 = integerStream4.reduce(0, Integer::sum, Integer::sum);
        System.out.println(l2);
    }
}
