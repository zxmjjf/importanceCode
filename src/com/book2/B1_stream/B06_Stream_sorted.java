package com.book2.B1_stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * <describe>sorted的用法</describe>
 * @author jjf
 * @version  2019-09-22 1.0.1
 */
public class B06_Stream_sorted {
    public static void main(String[] args) {
        Stream<String> stringStream = Stream.of("jjf1", "jiang", "", "1", "18312408434", "1997", "小狼", "ab");
        String[] strings = stringStream.toArray(String[]::new);
        System.out.println(Arrays.toString(strings));

        //自定义排序方式
        Stream<String> stringStream1 = Stream.of(strings).sorted(Comparator.comparing(String::length));
        String[] strings1 = stringStream1.toArray(String[]::new);
        System.out.println(Arrays.toString(strings1));

        //默认排序方式
        strings1 = Stream.of(strings).sorted().toArray(String[]::new);
        System.out.println(Arrays.toString(strings1));
    }
}
