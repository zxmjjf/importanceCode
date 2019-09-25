package com.book1.A8genericityt;

import java.util.Arrays;

public class CreateArrays {
    public static void main(String[] args) {
        String[] strings = create(String[]::new, 10,"jjf1", "jjf2");
        Integer[] integers = create(Integer[]::new, 5, 1, 11, 111, 121);

        System.out.println(Arrays.toString(strings));
        System.out.println(strings[1]);

        System.out.println(Arrays.toString(integers));

    }

    /**
     * <descrsbe> </descrsbe>
     * @param tCreate
     * @param lenth
     * @param ts :ts和 lenth可以包装到和方法有关的额数据域中，则可以省略这些参数，例如Stream的toArray方法就是如此。
     * @param <T>
     * @return
     */
    public static <T> T[] create(Create<T[]> tCreate, int lenth, T...ts) {
        T[] ts1 =  tCreate.create(lenth);
        for (int i = 0; i < ts.length; ++i) {
            ts1[i] = ts[i];
        }
        return ts1;
    }
}

@FunctionalInterface
interface Create<R>{
    R create(int var1);
}
