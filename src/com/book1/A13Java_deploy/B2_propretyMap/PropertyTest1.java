package com.book1.A13Java_deploy.B2_propretyMap;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

/**
 * @author book1
 * @verion 2019-09-18
 * <describe>属性映射的使用</describe>
 */
public class PropertyTest1 {
    public static void main(String[] args) throws IOException {
        test1();
    }

    private static void test1() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("Name", "姜剑锋");
        properties.setProperty("Id", "441581-19961004-5353");
        properties.setProperty("photo", "183-1240-7450");
        properties.setProperty("occupation", "Student");


        Path  path = Paths.get("src/com/book2/B3_XML/MyXML_1.xml");
        try (OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE);
             InputStream inputStream = Files.newInputStream(path, StandardOpenOption.CREATE)
             ) {
            properties.storeToXML(outputStream, "基本信息");

            //读取配置文件
            Properties properties1 = new Properties();
            properties1.loadFromXML(inputStream);
            System.out.println(properties1.get("photo"));

        }

    }
}
