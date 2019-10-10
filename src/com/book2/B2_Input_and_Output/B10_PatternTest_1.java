package com.book2.B2_Input_and_Output;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jjf
 * @version 2019-09-29  1.0.1
 * <describe> 正则表达式的查找和替换 </describe>
 */
public class B10_PatternTest_1 {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("c.t");
        String string = "one cat two cets in the yard c1t";
        Matcher m = p.matcher(string);
        StringBuilder sb = new StringBuilder();
        System.out.println(string + "\n" + p.toString() + "\n" + m.groupCount());
        while (m.matches()) {
            m.appendReplacement(sb, "dog");
            System.out.println(sb.toString());
            System.out.println(m.group()); //匹配到的组
            System.out.println(m.hitEnd());
            System.out.println();
            //System.out.println(m.group());
            //m.appendTail(sb);

            /*如果匹配器匹配到指定我模式字符串，则， 1.先将指定字符串拷贝到字符串流中， 再将指定字符串替换匹配
            * 到的原字符串部分，在字符串流中替换它
            * 即：非终端替换*/

            /* 注意此查找替换是惰性的，即sb不会追加最右一个查找到的内容之后的数据 */
            /*
            * matches()：尝试将整个区域与模式进行匹配。
            * */
        }

        /*System.out.println(string);
        System.out.println(sb.toString());
        System.out.println();
        m.appendTail(sb); //在字符串流sb中追加后面的内容。
        System.out.println(sb.toString());
        System.out.println(string);*/
    }
}
