package com.book2.B2_Input_and_Output;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 * @author jjf
 * @version 2019-09-27
 * <describe>文件的内存映射的用法</describe>
 */
public class B09_FileChannel {
    public static void main(String[] args) throws IOException {
        String filename = "D:\\qq\\config.xml.txd";
        Path path = Paths.get(filename);

        System.out.println("Input Stream:");
        long start = System.currentTimeMillis();
        long crcValue = inputStream(path);
        long end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " millisSeconds!");
        System.out.println();

        System.out.println("Access File:");
        start = System.currentTimeMillis();
        crcValue = accessFile(path);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " millisSeconds!");
        System.out.println();

        System.out.println("Map File:");
        start = System.currentTimeMillis();
        crcValue = mapFile(path);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " millisSeconds!");
        System.out.println();

        System.out.println("Buffer InputStream:");
        start = System.currentTimeMillis();
        crcValue = bufferInput(path);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end - start) + " millisSeconds!");


    }

    public static long inputStream(Path fileName) throws IOException {
        try (InputStream input = Files.newInputStream(fileName)) {
            CRC32 crc32 = new CRC32();
            int c;
            while ((c = input.read()) != -1) {
                crc32.update(c);
            }
            return crc32.getValue();
        }
    }

    public static long accessFile(Path fileName) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(fileName.toFile(), "r")) {
            long length = file.length();
            CRC32 crc32 = new CRC32();
            for (int i = 0; i < length; ++i) {
                file.seek(i);
                int c = file.readByte();
                crc32.update(c);
            }
            return crc32.getValue();
        }
    }

    public static long mapFile(Path fileName) throws IOException {
        try (FileChannel channel = FileChannel.open(fileName)) {
            CRC32 crc32 = new CRC32();
            int length = (int)channel.size();
            MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            for (int i = 0; i < length; i++) {
                int c = byteBuffer.get(i);
                crc32.update(c);
            }
            return crc32.getValue();
        }
    }

    public static long bufferInput(Path fileName)throws IOException {
        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(fileName))) {
            CRC32 crc32 = new CRC32();
            int c;
            while ((c = inputStream.read()) != -1) {
                crc32.update(c);
            }
            return crc32.getValue();
        }
    }
}
