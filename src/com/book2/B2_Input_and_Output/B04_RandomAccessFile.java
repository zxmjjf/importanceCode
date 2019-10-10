package com.book2.B2_Input_and_Output;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * @author jjf
 * @version 2019-09-25 1.0.1
 * <desctibe>随机存取文件数据</desctibe>
 * 结论：注意readline方法不完全支持UTF-8字符集。所以处理字符串最好用完整的字符串块即：修改的UTF-8
 * readUTF()方法。
 */
public class B04_RandomAccessFile {
    public static void main(String[] args) {
        try (RandomAccessFile fileIn = new RandomAccessFile("src/com/book2/B2_Input_and_Output/Text2.txt", "rw")) {
            long l = fileIn.length();
            System.out.println(l);
            System.out.println(fileIn.getFilePointer());

            //设置字节指针
            fileIn.seek(110);
            fileIn.writeChars("new String: 姜剑锋\n");
            System.out.println(fileIn.length() + "  " + fileIn.getFilePointer());

            //设置字节指针
            fileIn.seek(l);
            System.out.println(fileIn.readLine() + "jjf");

            //用readline()读取所有数据
            /*fileIn.seek(0);
            while (fileIn.length() - fileIn.getFilePointer() > 0) {
                System.out.println(fileIn.readLine());
            }//结果中文乱码*/

            //用readUTF()读取所有数据
            fileIn.seek(0);
            while (fileIn.length() - fileIn.getFilePointer() > 8) {
                System.out.println(fileIn.readLine());
            }//结果中文乱码，因为原字符串没有使用修改的UTF—8包装。

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
