package com.book1.A3;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class A3_2_PathsAndScanner {
    public static void main(String[] args){
        try (Scanner scanner = new Scanner(Paths.get("src/com/book1/A3/A3_2_Paths.txt"))) {
            //String string = scanner.nextLine();

            //System.out.println(string)
            //System.out.println(scanner.hasNextFloat());
            //System.out.println(scanner.nextLong());

            PrintWriter writer = new PrintWriter("src/com/book1/A3/A3_2_Paths.txt", "UTF-8");
            writer.println("jjf1");
            writer.println("jjf2");
            writer.println(" + jjf3");
            writer.write("jjf1");
            writer.append("jjf1 + jjf2 = jjf3");
            //writer.
            writer.write((int)97);
            /*一定要先关闭流，否则不能对此文件在操作*/
            writer.close();

            System.out.println(scanner.hasNextInt());
            System.out.println(scanner.hasNextByte());
            System.out.println(scanner.nextLine());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("异常");
        }

    }
}
