package com.jjf.A9collectionAPI.API_3_LinkedLIst;

import java.util.LinkedList;

public class Stack {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("jjf1");
        linkedList.add("jjf2");
        linkedList.add("jjf3");
        linkedList.add("jjf4");
        linkedList.push("jjf5");

        System.out.println(linkedList.pop());
        System.out.println(linkedList.pop());
        System.out.println(linkedList.peek());
    }
}
