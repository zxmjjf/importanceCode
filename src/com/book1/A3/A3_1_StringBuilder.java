package com.book1.A3;

public class A3_1_StringBuilder {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("jjf1");
        stringBuilder.append(66666);
        stringBuilder.append("jjf2");
        String string1 = stringBuilder.toString();
        System.out.println(string1);
        System.out.println(stringBuilder);
        System.out.println(stringBuilder.toString());
        System.out.println();

        stringBuilder.append(2000);
        stringBuilder.append("jjf3");
        String string2 = stringBuilder.toString();
        System.out.println(string2);
        System.out.println(stringBuilder);
        System.out.println(stringBuilder.toString());
        System.out.println();

        stringBuilder = stringBuilder.reverse();
        System.out.println(stringBuilder.toString());
    }
}
