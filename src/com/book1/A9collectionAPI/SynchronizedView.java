package com.book1.A9collectionAPI;

import java.util.*;

public class SynchronizedView {
    public static void main(String[] args) {
        List<Object> arrayList = Collections.synchronizedList(new ArrayList<>());
        //List<Integer> arrayList = Collections.checkedList(new ArrayList<>(), Integer.class);
        //ArrayDeque arrayDeque = (ArrayDeque) Collections.synchronizedList(new ArrayDeque<>());
        List<Object> linkedList = Collections.synchronizedList(new LinkedList<>());
        Set<Object> hashSet =  Collections.synchronizedSet(new HashSet<>());
        Set<Object> treeSet =  Collections.synchronizedSet(new TreeSet<>());
        Map<Object, Object> hashMap =  Collections.synchronizedMap(new HashMap<>());
        Map<Object, Object> treeMap =  Collections.synchronizedMap(new TreeMap<>());

        arrayList.add("book1");
        arrayList.add(2);
        System.out.println(arrayList);
        Collections.reverse(arrayList);
        System.out.println(arrayList);
        linkedList.add("jjf1");
        linkedList.add(2);
        System.out.println(linkedList);
        arrayList.remove(1);
        System.out.println(arrayList);

        hashMap.put("jjf1", "zxm1");
        hashMap.put(1, 3);
        hashMap.put(2, "book1");
        System.out.println(hashMap);
        hashMap.remove(1);
        System.out.println(hashMap);
    }
}
