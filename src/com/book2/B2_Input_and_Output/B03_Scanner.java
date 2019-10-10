package com.book2.B2_Input_and_Output;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author jjf
 * @version 2019-09-25
 * <describe>Scanner构造器的参数</describe>
 */
public class B03_Scanner {
    public static void main(String[] args) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/com/book2/B2_Input_and_Output/Text2.txt",true), 100);
            FileOutputStream fileOutputStream = new FileOutputStream("src/com/book2/B2_Input_and_Output/Text2.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/com/book2/B2_Input_and_Output/Text2.txt"), 100);
            FileInputStream fileInputStream = new FileInputStream("src/com/book2/B2_Input_and_Output/Text2.txt");

            //最外层读写器
            //PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
            PrintWriter printWriter = new PrintWriter(fileOutputStream, true);
            Scanner scanner = new Scanner(bufferedReader);

            //写数据
            printWriter.write("姜剑锋 \\is 22 years old\n");
            printWriter.write("姜剑锋 \\tis a clever boy\n");
            printWriter.flush(); //一定要flush();

            //读数据
            Stream<String> string = scanner.tokens(); //将scanner所能读的所有数据转化为字符串流：过滤空格，制表符，换行等不可见字符
            string.forEach(v -> System.out.print(v + "  "));
            System.out.println();
            //显示结果
            //System.out.println(string);
            System.out.println(Charset.defaultCharset());
            System.out.println(Charset.availableCharsets());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
