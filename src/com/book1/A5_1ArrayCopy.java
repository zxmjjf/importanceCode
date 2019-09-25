package com.book1;

import java.lang.reflect.Array;
import java.util.Arrays;

public class A5_1ArrayCopy {
    public static void main(String[] args) {
        int[] anInts = {1, 2, 3, 4, 9};
        String[] strings = {"jjf1", "0000", "aabb"};
        Object[] objects = {"1234", 123, "book1", anInts, strings};
        System.out.println(Arrays.toString(anInts) + "\n" + Arrays.toString(strings) + "\n" + Arrays.toString(objects));

        int[] copy1 = (int[])arrayCopy1(anInts, 10);
        String[] copy2 = (String[])arrayCopy1(strings, 10);
        Object[] copy3 = (Object[])arrayCopy1(objects, 10);
        System.out.println(Arrays.toString(copy1) + "\n" + Arrays.toString(copy2) + "\n" + Arrays.toString(copy3));
    }

    /**
     * <describe>使用泛型技术和反射技术实现数组拷贝函数</describe>
     * @param array
     * @param newLength
     * @param <T>
     * @return
     */
    public static <T> T arrayCopy(T array, int newLength) {
        Class arrayClass = array.getClass();
        if (!arrayClass.isArray()) {
            return null;
        }
        Class componentType = arrayClass.getComponentType();
        int length = Array.getLength(array);
        T newArray = (T)Array.newInstance(componentType, length);
        System.arraycopy(array, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }

    public static Object arrayCopy1(Object array, int newLength) {
        Class arrayClass = array.getClass();
        if (!arrayClass.isArray()) {
            return null;
        }
        Class componentType = arrayClass.getComponentType();
        int length = Array.getLength(array);
        Object newArray = Array.newInstance(componentType, length);
        System.arraycopy(array, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}
