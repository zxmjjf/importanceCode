package com.book2.B2_Input_and_Output;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author jjf
 * @version 2019-09-27
 * Files类的常用用法
 */
public class B08_Files {
    public static void main(String[] args) throws IOException {
        byte[] bytes;
        String string;
        Stream<String> stringStream;
        List<String> list;

        //read file
        Path path = Paths.get("src/com/book2/B2_Input_and_Output/Text1.txt");
        if (!path.toFile().isFile()) {
            Files.createFile(path);
        }
        bytes = Files.readAllBytes(path);
        string = Files.readString(path, StandardCharsets.UTF_8);
        stringStream = Files.lines(path, StandardCharsets.UTF_8);
        list = Files.readAllLines(path, StandardCharsets.UTF_8);
        // print result
        System.out.println(Arrays.toString(bytes));
        System.out.println(string);
        stringStream.forEach(v -> System.out.print(v + "\t"));
        System.out.println();
        list.forEach(v -> System.out.print(v + "\t"));
        System.out.println();

        //write file
        Path path1 = Paths.get("src/com/book2/B2_Input_and_Output/Text3.txt");
        if (!path.toFile().isFile()) {
            Files.createFile(path1);
        }
        Files.write(path1, bytes);
        Files.writeString(path, string);
        Files.write(path1, list,StandardCharsets.UTF_16);

        //deal with big file

        InputStream inputStream;
        OutputStream outputStream;
        Reader reader;
        Writer writer;

        //构造字节流
        Path path2 = Paths.get("E:\\newIDEA\\importanceCode\\src\\com\\book2\\B2_Input_and_Output\\Text6.txt");
        /*//Files.createFile(path2);
        if (!path.toFile().isFile()) {
            Files.createFile(path2);
        }*/
        outputStream = Files.newOutputStream(path2, StandardOpenOption.CREATE);
        inputStream = Files.newInputStream(path2);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        //write into
        dataOutputStream.writeUTF(string);
        dataOutputStream.flush();
        //read out
        System.out.println(dataInputStream.readUTF());

        //构造字符流
        Path path3 = Paths.get("E:\\newIDEA\\importanceCode\\src\\com\\book2\\B2_Input_and_Output\\Text7.txt");
        //Files.createFile(path3);
        /*if (!path.toFile().isFile()) {
            Files.createFile(path3);
        }*/
        writer = Files.newBufferedWriter(path3, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        reader = Files.newBufferedReader(path3, StandardCharsets.UTF_8);
        PrintWriter printWriter = new PrintWriter(writer, true);
        Scanner scanner = new Scanner(reader);
        //write into
        printWriter.println(string);
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }


    }
}
