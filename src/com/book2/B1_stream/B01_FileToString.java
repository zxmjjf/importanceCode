package com.book2.B1_stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class B01_FileToString {
    public static void main(String[] args) {
        String files = null;
        try {
            files = Files.readString(Paths.get("src/com/book2/B1_stream/FileObject.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(files);



        /*将String转化为集合*/
        assert files != null;
        List<String> list = Arrays.asList(files.split("\\PL+"));
        //System.out.println(list);

        Stream<String> stream = list.stream();
        Stream<String> stream1 = stream.map(v -> v + "jjf");

        System.out.println(stream1.findFirst().orElse("non"));

        Stream<String> stream2 = list.stream();
        System.out.println(stream2.distinct().count());

    }
}
