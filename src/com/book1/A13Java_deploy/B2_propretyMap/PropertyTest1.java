package com.book1.A13Java_deploy.B2_propretyMap;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author book1
 * @verion 2019-09-18
 * <describe>属性映射的使用</describe>
 */
public class PropertyTest1 {
    public static void main(String[] args) {
        test1();
    }

    private static void test1(){
        Properties properties = new Properties();
        properties.setProperty("Name", "姜剑锋");
        properties.setProperty("Id", "441581-19961004-5353");
        properties.setProperty("photo", "183-1240-8434");
        properties.setProperty("occupation", "Student");

        System.out.println("1");
        System.out.println(properties);

        Properties properties1 = new Properties();

        /*try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/com/book1/A14propretyMap/PropertyTable1");
            properties.store(fileOutputStream, "个人基本信息");
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*try (FileOutputStream out = new FileOutputStream("src/com/book1/A14propretyMap/PropertyTable1");
             FileInputStream in = new FileInputStream("src/com/book1/A14propretyMap/PropertyTable1")) {
            out.write('\n');
            properties.store(out, "informations!!!");
            properties1.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("2");
        System.out.println(properties1);
        */

        Properties properties2 = new Properties();
        try (FileInputStream inputStream = new FileInputStream("src/com/book1/A13Java_deploy/B2_propretyMap/PropertyTable1")) {
            properties2.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("3");
        System.out.println(properties2);

    }
}
