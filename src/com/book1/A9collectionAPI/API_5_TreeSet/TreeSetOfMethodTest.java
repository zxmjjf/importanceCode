package com.jjf.A9collectionAPI.API_5_TreeSet;

import java.util.*;

public class TreeSetOfMethodTest {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet((Comparator<Integer>) (o1, o2) -> o2 - o1);
        Random random = new Random(1000);
        for (int i = 0; i < 30; ++i) {
            treeSet.add(random.nextInt(200));
        }
        Iterator iterator = treeSet.descendingIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");

        }
        System.out.println();

        NavigableSet navigableMap = treeSet.descendingSet();
        System.out.println(navigableMap);

        SortedSet sortedSet = treeSet.headSet(93);
        System.out.println(sortedSet);
        NavigableSet navigableSet1 = treeSet.headSet(93, false);
        System.out.println(navigableSet1);

        System.out.println(treeSet);
        System.out.println(treeSet.size());
    }
}
