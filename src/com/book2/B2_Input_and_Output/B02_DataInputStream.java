package com.book2.B2_Input_and_Output;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author jjf
 * @version 2019-09-24 1.0.1
 * <describe>数据输入流：DataInputStream</describe>
 */
public class B02_DataInputStream {
    public static void main(String[] args) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/com/book2/B2_Input_and_Output/Text3.txt");
            //DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            FileInputStream fileInputStream = new FileInputStream("src/com/book2/B2_Input_and_Output/Text3.txt");
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            /*//写入字符串：
            String string = Files.readString(Paths.get("src/com/book2/B2_Input_and_Output/Text1.txt"));
            dataOutputStream.writeUTF(string);
           byte[] bytes = {1, 2, 4, 6, 8, 10};
            System.out.println(Arrays.toString(bytes));
            dataOutputStream.writeUTF(Arrays.toString(bytes));
            dataOutputStream.flush();*/
            /*System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.available());
            System.out.println(dataInputStream.readUTF());*/

            //写int数据
            /*Random random = new Random(0);
            IntStream intStream = random.ints(20, -20, 20);
            intStream = intStream.peek(v -> System.out.print(v + "   "));

            intStream.forEach(v -> {
                try {
                    dataOutputStream.writeInt(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            dataOutputStream.flush();*/

            PrintWriter printWriter = new PrintWriter(fileOutputStream);
            printWriter.write('a');
            printWriter.println("jjf134");
            printWriter.println(123);
            printWriter.close();

            System.out.println(dataInputStream.readChar());
            System.out.println(dataInputStream.readChar());
            System.out.println(dataInputStream.readChar());
            System.out.println(dataInputStream.readChar());
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readChar());

            /*System.out.println();
            while (dataInputStream.available() > 0) {
                System.out.println(dataInputStream.available() + "--> " + dataInputStream.readInt());
            }*/




            dataInputStream.close();
            //dataOutputStream.close();
            fileInputStream.close();
            //fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
