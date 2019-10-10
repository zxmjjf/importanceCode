package com.book2.B2_Input_and_Output;

import java.io.*;

/**
 * @author jjf
 * @version 2019-09-27
 * <describe>使用对象序列化深克隆</describe>
 *
 */
public class B07_SerialCloneable {
    public static void main(String[] args) throws CloneNotSupportedException {
        MyClass myClass = new MyClass("jjf", new Workdate(2019, 9, 27));
        MyClass myClass1 = myClass.clone();
        System.out.println(myClass);
        System.out.println(myClass1);

        myClass.setInWorkTime(2019, 8, 1);
        System.out.println(myClass);
        System.out.println(myClass1);

    }
}



class SerialCloneable implements Cloneable, Serializable {

}
