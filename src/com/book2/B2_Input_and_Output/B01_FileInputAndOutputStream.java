package com.book2.B2_Input_and_Output;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author jjf
 * @version 2019-09-24 1.0.1
 * <describe>FileInputStream 和 FileInputSteam 的用法</describe>
 */
public class B01_FileInputAndOutputStream {
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("src/com/book2/B2_Input_and_Output/Text1.txt");
             FileOutputStream fileOutputStream = new FileOutputStream("src/com/book2/B2_Input_and_Output/Text1.txt", true)) {
            byte[] bytes = {1, 2, 4, '\n', 'a', 'b', 'c', '1', '2', '3'};
            System.out.println(Arrays.toString(bytes));

            //write in fileOutStream
            fileOutputStream.write(bytes);
            fileOutputStream.flush();

            //read on fileInputSteam
            bytes = fileInputStream.readAllBytes();
            System.out.println(Arrays.toString(bytes));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
