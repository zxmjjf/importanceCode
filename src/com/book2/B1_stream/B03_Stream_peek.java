package com.book2.B1_stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jjf
 * @version 2019-09-22 1.0.0.1
 * <describe>peek(FunctionInterface)|的用法 </describe>
 */
public class B03_Stream_peek {
    public static void main(String[] args) {
        //test1
        Stream<String> stringStream = Stream.of("jjf1", "jiang", "18312408434", "1997", "小狼");
        List<String> list = stringStream.peek(v -> System.out.println("【" + v + "】")).sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
        System.out.println(list);

        //test2
        Stream<String> stringStream1 = Stream.of("jjf1", "jiang", "18312408434", "1997", "小狼");
        System.out.println(stringStream1.peek(v -> System.out.println("【" + v + "】")).findFirst());
    }
}
