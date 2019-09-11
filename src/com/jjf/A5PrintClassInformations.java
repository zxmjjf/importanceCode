package com.jjf;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author jjf
 * @version 1.0.1  2019-9-11
 * <descibe>通过反射，打印类的基本信息</descibe>
 */
public class A5PrintClassInformations {
    public static void main(String[] args) {
        String className = "java.lang.Math";
        printClass(className);
    }

    public static void printClass(String className) {
        try{
            Class name = Class.forName(className);
            Class superClass = name.getSuperclass();
            String modifiers = Modifier.toString(name.getModifiers());

            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print("class " + name.getName());
            if (superClass != null && superClass != Object.class) {
                System.out.print(" extends " + superClass.getName());
            }
            System.out.println("{\n\t//数据域");
            printFields(name);
            System.out.println("\n\t//构造方法");
            printConstructors(name);
            System.out.println("\n\t//方法");
            printMethods(name);
            System.out.println("}\n");

        } catch (ClassNotFoundException e) {
            System.out.println("注意：未找到相应的类！！！");
        }
    }

    /**
     * <describe>打印类的构造方法</describe>
     * @param c1
     */
    public static void printConstructors(Class c1) {
        Constructor[] constructors = c1.getDeclaredConstructors();

        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            System.out.print("\t");
            String modifiers = Modifier.toString(constructor.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(name + "(");

            //打印参数列表
            Class[] parameter = constructor.getParameterTypes();
            for (int i = 0; i < parameter.length; ++i) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(parameter[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * <describe>打印类的方法，不包括父类的</describe>
     * @param c1
     */
    public static void printMethods(Class c1) {
        Method[] methods = c1.getDeclaredMethods();

        for (Method method : methods) {
            Class returnType = method.getReturnType();
            String name = method.getName();
            String modifiers = Modifier.toString(method.getModifiers());

            System.out.print("\t");
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(returnType.getName() + " " + name + "(");

            //打印形参列表
            Class[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; ++i) {
                if (i > 0) {
                    System.out.print(", ");
                    System.out.print(parameterTypes[i].getName());
                }
            }
            System.out.println(");");
        }
    }

    /**
     * <describe>打印类的数据域，不包括父类的</describe>
     * @param c1
     */
    public static void printFields(Class c1) {
        Field[] fields = c1.getFields();

        for (Field field : fields) {
            Class type  = field.getType();
            String name = field.getName();
            String modifiers = Modifier.toString(field.getModifiers());

            System.out.print("\t");
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.println(type.getName() + " " + name + ";");
        }
    }
}
