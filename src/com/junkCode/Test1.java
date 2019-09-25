package com.junkCode;

import java.util.Scanner;
public class Test1{
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int value = scanner.nextInt();
            long sum = 0;
            for (int i = 1; i <= value; ++i) {
                sum += i;
            }
            System.out.println("1..." + value +  " = " + sum);
        }
    }
}
