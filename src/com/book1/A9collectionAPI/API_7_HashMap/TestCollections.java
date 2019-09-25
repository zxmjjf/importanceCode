package com.book1.A9collectionAPI.API_7_HashMap;

import java.util.*;

public class TestCollections {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.putAll(Collections.singletonMap(1, "jjf1"));
        hashMap.putAll(Collections.singletonMap(2, "jjf2"));
        hashMap.putAll(Collections.singletonMap(3, "jjf3"));
        hashMap.putAll(Collections.singletonMap(4, "jjf1"));
        hashMap.putAll(Collections.singletonMap(5, "jjf1"));
        hashMap.putAll(Collections.singletonMap(6, "jjf1"));
        System.out.println(hashMap);


        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList("jjf1", "jjf2", 1, 3, "zxm0"));
        System.out.println(arrayList);
        List arrayList1 = arrayList.subList(2, 5);
        System.out.println(arrayList1);
        arrayList1.clear();
        System.out.println(arrayList);

        Map hashMap1 = Collections.unmodifiableMap(hashMap);
        System.out.println(hashMap1);
        System.out.println(hashMap1.get(4));

    }
}
