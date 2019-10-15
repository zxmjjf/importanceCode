package com.junkCode;
import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        int num = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("程序开始执行！");
        while (num < 5 && scanner.hasNextLine()) {
            ++num;
            System.out.println(scanner.nextLine());
            System.out.println("hasNextLine in while");
            if (num == 5) {
                scanner.close();
            }
        }
        if (scanner.hasNextLine()) {
            System.out.println("hasNextLine in if");
        } else {
            System.out.println("程序执行结束, if 语句不成立！");
        }

    }
}
