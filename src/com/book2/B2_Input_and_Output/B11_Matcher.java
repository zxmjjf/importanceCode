package com.book2.B2_Input_and_Output;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class B11_Matcher {
    public static void main(String[] args) {
        String line = "jjjfThat order was placed for QT 666 ！ok";
        String string1 = "(.*?)(\\d+)(.*)";
        String string2 = "jjjf(?=This|this|That)(.*?)(\\d+)";


        Pattern pattern1 = Pattern.compile(string1);
        Pattern pattern2 = Pattern.compile(string2);

        Matcher newMatcher1 = pattern1.matcher(line);
        Matcher newMatcher2 = pattern2.matcher(line);


        if (newMatcher1.find()) {
            int group = newMatcher1.groupCount();
            System.out.println(group);
            for (int i = 0; i <= group; i++) {
                System.out.println("group[" + i + "] = " + newMatcher1.group(i));
            }
        }
        System.out.println(newMatcher1.matches());


        System.out.println();
        if (newMatcher2.find()) {
            int group = newMatcher2.groupCount();

            System.out.println(group);
            for (int i = 0; i <= group; i++) {
                System.out.println("group[" + i + "] = " + newMatcher2.group(i));
            }
        }
        System.out.println(newMatcher2.matches());
    }
}
