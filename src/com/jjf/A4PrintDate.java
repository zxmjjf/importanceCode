package com.jjf;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author jjf
 * @version 2019-9-11  1.0.1
 * <descirbe>打印当前日历</descirbe>
 */
public class A4PrintDate {
    public static void main(String[] args) {
        //获取当前时间
        //LocalDate localDate = LocalDate.now();
        LocalDate localDate = LocalDate.of(2019, 9, 1);
        int month = localDate.getMonthValue();
        int today = localDate.getDayOfMonth();
        System.out.println("************* " + localDate.toString() + " **************");

        localDate = localDate.minusDays(today - 1);
        DayOfWeek weekday = localDate.getDayOfWeek();
        int value = weekday.getValue(); //获得星期时间的int值
        System.out.println("Sun   Mon   Tue   Wed   The   Fri   Sat");
        if (value != 7)
        {
            for (int i = 0; i < value; ++i) {
                System.out.print("      "); //找到第一个日期的位置
            }
        }
        while (localDate.getMonthValue() == month) {
            System.out.printf("%3d", localDate.getDayOfMonth()); //打印时间
            if (localDate.getDayOfMonth() == today) {
                System.out.print("*  "); //控制日期输出间隔
            } else{
                System.out.print("   "); //控制日期输出间隔
            }
            localDate = localDate.plusDays(1); //指向下一天
            if (localDate.getDayOfWeek().getValue() == 7) { //如果是星期天，则换行输出
                System.out.println();
            }
        }
        System.out.println("\n**************************************");
        if (localDate.getDayOfWeek().getValue() != 7) {
            System.out.println();
        }

    }
}
