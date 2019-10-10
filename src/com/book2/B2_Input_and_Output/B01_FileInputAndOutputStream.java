package com.book2.B2_Input_and_Output;

import knowledge.ByteToOtherBaseData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author jjf
 * @version 2019-09-24 1.0.1
 * <describe>FileInputStream 和 FileInputSteam 的用法</describe>
 */
public class B01_FileInputAndOutputStream {
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("src/com/book2/B2_Input_and_Output/Text1.txt");
             FileOutputStream fileOutputStream = new FileOutputStream("src/com/book2/B2_Input_and_Output/Text1.txt")) {
            byte[] bytes = {1, 2, 4, '\n', 'a', 'b', 'c', '1', '2', '3'};
            System.out.println(Arrays.toString(bytes));

            //write in fileOutStream
            PrintWriter printWriter = new PrintWriter(fileOutputStream, true);
            printWriter.write("jjf is a 大傻逼");
            printWriter.println(Arrays.toString(bytes));
            //printWriter.flush();
            fileOutputStream.write(bytes);


            //read on fileInputSteam
            /*bytes = fileInputStream.readAllBytes();
            System.out.println(bytes.length);
            String string = ByteToOtherBaseData.getString(bytes, "UTF-8");
            System.out.println(string);*/
            String string = Files.readString(Paths.get("src/com/book2/B2_Input_and_Output/Text1.txt"));
            System.out.println(string);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
