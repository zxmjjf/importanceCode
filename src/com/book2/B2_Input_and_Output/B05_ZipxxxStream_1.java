package com.book2.B2_Input_and_Output;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class B05_ZipxxxStream_1 {
    public static void main(String[] args) {
        read();
        //write();
    }

    /**
     * <describe>读出压缩文件的内容</describe>
     */
    private static void read() {
        try{
            File file = new File("src/com/book2/B2_Input_and_Output/MytestZip_1.zip");
            ZipFile zipFile = new ZipFile(file, ZipFile.OPEN_READ,StandardCharsets.UTF_8);
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
            ZipEntry[] zipEntrys = new ZipEntry[10];
            InputStream inputStream;

            int i = 0;
            while ((zipEntrys[i] = zipInputStream.getNextEntry()) != null) {
                inputStream = zipFile.getInputStream(zipEntrys[i]);
                Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);

                //读文件的前10行
                System.out.println();
                System.out.println("第" + (i + 1) + "个压缩文件" + zipEntrys[i].getName() + ",其大小为" + zipEntrys[i].getCompressedSize()
                        + "\t压缩方法为：" + zipEntrys[i].getMethod());

                for (int j = 0; j < 10; ++j) {
                    if (scanner.hasNextLine()){
                        System.out.println(scanner.nextLine());
                    }
                }
                System.out.println(".........");
                ++i;
            }
            ////////////////////////////////////////////////
            File zipFile1 = new File("src/com/book2/B2_Input_and_Output/MytestZip_1.zip");
            //压缩文件流
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile1, true);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(zipOutputStream, true);

            //压缩文件名
            //String[] strings = {"Text1.txt", "Text2.txt", "Text3.txt", "Text4_Scanner.txt"};

            zipOutputStream.setComment("jjf");
            for (int k = 0; k < 4; ++k) {
                //zipEntry = new ZipEntry(strings[i]);
                assert zipEntrys[k] != null;
                zipOutputStream.putNextEntry(zipEntrys[k]); //新建或者覆盖新的压缩文件
                //将将数据写入压缩文件
                printWriter.println("\n【姜剑锋~!@#$%^&**())_+{}|?><】");
                printWriter.println("1 2 3 木头人！！");

                zipOutputStream.closeEntry();

            }

            //关闭流
            printWriter.close();
            fileOutputStream.close();
            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void write(){
        /**
         * 写压缩文件不是针对压缩已有压缩文件的更新
         * 而是新建新的压缩文件或者覆盖原有的压缩文件。
         * 说，流的最顶层的文件对象是要新建或者覆盖的压缩文件
         * 压缩流的 putNextEntry()方法 和 closeEntry()方法是定位和关闭压缩文件项。
         * 文件项由 ZipEntry类新建
         */
        try{
            File zipFile = new File("src/com/book2/B2_Input_and_Output/MytestZip_1.zip");
            //压缩文件流
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile, true);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(zipOutputStream, true);

            //压缩文件名
            String[] strings = {"Text1.txt", "Text2.txt", "Text3.txt", "Text4_Scanner.txt"};

            zipOutputStream.setComment("jjf");
            for (int i = 0; i < strings.length; ++i) {
                //zipEntry = new ZipEntry(strings[i]);
                zipOutputStream.putNextEntry(new ZipEntry(strings[i])); //新建或者覆盖新的压缩文件
                //将将数据写入压缩文件
                printWriter.println("\n【姜剑锋~!@#$%^&**())_+{}|?><】");
                printWriter.println("1 2 3 木头人！！");
                zipOutputStream.closeEntry();

            }

            //关闭流
            printWriter.close();
            fileOutputStream.close();
            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
