package com.book1;

/**
 * <describe>通过方法引用创建泛型对象的实例</describe>
 * @author book1
 * @version 1.0.1  2019-09-12
 */
public class A6_0Lambda {
    public static <T> T make(CreateObjet<T> createObjet, Object... objects) {
        return createObjet.create(objects);
    }

    public static void main(String[] args) {
        MyClass myClass1 = make(MyClass::new, 3, "jjf0");
        MyClass myClass2 = make(MyClass::new, "jjf1", 5, 4);
        System.out.println(myClass1.toString());
        System.out.println(myClass2.toString());
    }
}

/**
 *
 * @param <T>
 */
@FunctionalInterface
interface CreateObjet<T>{
    T create(Object... ts);

}

class MyClass{
    int iD;
    String name;
    MyClass(){
        iD = 0;
        name = "book1";
    }

    MyClass(Object... objects) {
        this();
        for (int i = 0; i < objects.length; ++i) {
            if (objects[i] instanceof Integer) {
                iD = (int)objects[i];
            } else if (objects[i] instanceof String) {
                name = (String)objects[i];
            }
        }
    }



    @Override
    public String toString() {
        return iD + "\t" + name;
    }
}
