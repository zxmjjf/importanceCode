package com.book2.B1_stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class B02_FilesLineToStream {
    public static void main(String[] args) {
        try{
            Stream<String> stringStream = Files.lines(Paths.get("src/com/book2/B1_stream/FileObject.txt"));
            System.out.println(stringStream.count());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
