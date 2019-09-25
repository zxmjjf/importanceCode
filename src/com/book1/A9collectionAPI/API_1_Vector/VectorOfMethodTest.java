package com.jjf.collectionAPI.API_1_Vector;

import java.util.ListIterator;
import java.util.Vector;

/**
 * Vector类的特性
 * 1）元素的 index 值由 0 开始
 * 2）结构上类似于数组，但有几点不同
 *      a) 数组使用必须先确定容量，且容量确定之后不能扩容。
 *      b）对数组的操作由下标随机访问，且数组类型数据自定义的域合方法只有 length 合 clone(),其他的由Object继承
 *      b）Vector 向量类不支持下标访问，Vector的容量可以再容量不足时自动扩张
 */
public class VectorOfMethodTest {
    private int[] arr;

    public static void main(String[] args) {
        test4();
    }

    /**
     * 测试 add() 与 addElement() 的区别
     * 结论： 无差别
     */
    private static void test1(){
        System.out.println("****** This in test1 method");

        Vector vector = new Vector(6);
        Vector vector1 = new Vector(6);

        /** use add() method */
        System.out.println(vector.capacity() + "  " + vector.size());
        vector.add("11");
        System.out.println(vector.capacity() + "  " + vector.size());
        vector.add("11");
        vector.add("11");
        vector.add("11");
        vector.add("11");
        vector.add("11");
        vector.add("11");
        System.out.println(vector);
        System.out.println(vector.capacity() + "  " + vector.size());
        System.out.println();

        /** ues addElement() method */
        System.out.println(vector1.capacity() + "  " + vector1.size());
        vector1.addElement(12);
        System.out.println(vector1.capacity() + "  " + vector1.size());
        vector1.addElement(12);
        vector1.addElement(12);
        vector1.addElement(12);
        vector1.addElement(12);
        vector1.addElement(12);
        vector1.addElement(12);
        System.out.println(vector1);
        System.out.println(vector1.capacity() + "  " + vector1.size());
        System.out.println();
    }

    /**
     * 测试：是否可以：使用一个 Vector 集合，存放不同类型的数据
     * 结论：没有给定类型参数的 Vector 类，可以存放任意类型的数据
     *      给定了类型参数的 Vector 类，只能存放指定类型的数据
     */
    private static void test2(){
        Vector vector = new Vector();
        vector.addElement(1);
        vector.addElement('a');
        vector.addElement("String");
        vector.addElement(new int[]{2, 1, 0});

        System.out.println(vector);
        vector.set(2, 44);
        System.out.println(vector);

        Vector<String> vector1 = new Vector<>();
        vector1.addElement("jjf");
        /*vector1.addElement(2);
        vector1.addElement(new int[]{3, 2, 1, 0});*/ //error
        System.out.println(vector1);
    }

    /**
     * 测试：setSize() 方法的用法
     * 结论：setSize()方法修改的是 Vector.size的值，如果size的值设置变大了，用 null 填充
     *      如果设置变小了，之后的元素相当于删除，即使再设置回去，也只能由 null 来填充
     */
    private static void test3(){
        Vector vector = new Vector();
        System.out.println("vector: " + vector + "  size: " + vector.size());
        vector.addElement("jjf1");
        vector.addElement("jjf2");
        vector.addElement("jjf3");
        System.out.println("vector: " + vector + "  size: " + vector.size());

        /** setSize() */
        vector.setSize(6);
        System.out.println("vector: " + vector + "  size: " + vector.size());

        vector.setSize(2);
        System.out.println("vector: " + vector + "  size: " + vector.size());

        vector.setSize(4);
        System.out.println("vector: " + vector + "  size: " + vector.size());

    }

    /**
     * 测试：listIterator() method
     * 结论：无参数的 listIterator() method， 迭代器的位置在 index 为 0 元素之前
     *      有参数的 listIterator(indexValue) method, 迭代器的位置在 index 为 0 indexValue 元素之前
     */
    private static void test4(){
        Vector vector = new Vector();

        /** add Element */
        for (int i = 0; i < 7; ++i) {
            String jjf  = "jjf " + i;
            vector.addElement(jjf);
        }

        ListIterator iterator = vector.listIterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next() + "   ");
        }
        System.out.println();

        ListIterator iterator1 = vector.listIterator(2);
        System.out.println(iterator1.nextIndex() + "    " +  iterator1.nextIndex() + "   " +
                iterator1.previousIndex() + "   " + iterator1.previousIndex());
    }

    /**
     * 测试remove(Object) 与
     */
    private static void test5(){

    }

}
