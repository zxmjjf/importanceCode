package com.jjf.collectionAPI.API_3_LinkedLIst;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author jjf
 * @version 1.0.1 2019-08-15
 * 描述：测试 LinkedList 类的常用方法
 */
public class LinkedListOfMethodTest {
    public static void main(String[] args) {
        test1();
    }

    /**
     * test: remove(Object)
     */
    private static void test1(){
        LinkedList linkedList = new LinkedList();
        linkedList.add("jjf");
        linkedList.add("jjf");
        System.out.println(linkedList);
        linkedList.remove("jjf");
        System.out.println(linkedList);

    }
}
