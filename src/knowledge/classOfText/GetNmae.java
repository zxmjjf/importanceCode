package knowledge.classOfText;


import java.lang.reflect.Field;

/**
 * @author book1
 * @version 1.0.1
 * 描述：测试Class类中的方法
 * 1. getName方法放回的类名是否是实际类型
 */
public class GetNmae {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException{
        Parent var1 = new Parent(1);
        Parent var2 = new Child(2);
        Child var3 = new Child(3);

        Class c1 = var3.getClass();
        Field field = c1.getDeclaredField("value");
        field.setAccessible(true);

        System.out.println(field.get(var3)); //3
        field.set(var3, 4);

        System.out.println(field.get(var3));
        System.out.println(var3.getValue());
        System.out.println(field.getInt(var3));


        /*Field[] fileds = Child.class.getFields();
        for (Field field : fileds) {
            System.out.println(field.getName());
            System.out.println(field.getDeclaringClass());
            field.setAccessible(true);
            System.out.println(field.get(parent2));
        }*/

        /*System.out.println(Child.class.getDeclaringClass());

        Class class1 = Class.class;
        Method[] methods = class1.getMethods();
        for (Method method : methods) {
            System.out.print(method.getName() + "|||");

            Class[] clas2 = method.getExceptionTypes();
            for (Class c : clas2) {
                System.out.print(c.getName() + "||");
            }
            System.out.println();
        }*/


    }
}

/**
 * 父类
 */
class Parent{
    private int value;

    /**
     * 构造器
     * @param value
     */
    Parent(int value) {
        this.value = value;
    }

    Parent(){

    }


}

/**
 * 子类，扩展 Parent
 */
class Child extends Parent{
    private int value;

    Child(int value) {
        super(value);
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    Child(){
    }


}


