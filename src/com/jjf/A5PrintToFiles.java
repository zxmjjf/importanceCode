package com.jjf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Paths;

/**
 * @author jjf
 * @version 1.0.1  2019-9-11
 * <descibe>通过反射，打印类的基本信息，并将类的信息输出到classInformations.txt文件中</descibe>
 */
public class A5PrintToFiles {
    public static PrintWriter printWriter;

    public static void main(String[] args) {
        try {
            printWriter = new PrintWriter("src/com/jjf/classInformations.txt", "UTF-8");
        } catch (IOException e) {
            printWriter.println("文件处理异常");
        }

        String className = "java.lang.Mathss";
        printClass(className);

        printWriter.close();
    }

    public static void printClass(String className) {
        try{
            Class name = Class.forName(className);
            Class superClass = name.getSuperclass();
            String modifiers = Modifier.toString(name.getModifiers());

            if (modifiers.length() > 0) {
                printWriter.print(modifiers + " ");
            }
            printWriter.print("class " + name.getName());
            if (superClass != null && superClass != Object.class) {
                printWriter.print(" extends " + superClass.getName());
            }
            printWriter.println("{\n\t//数据域");
            printFields(name);
            printWriter.println("\n\t//构造方法");
            printConstructors(name);
            printWriter.println("\n\t//方法");
            printMethods(name);
            printWriter.println("}\n");

        } catch (ClassNotFoundException e) {
            printWriter.println("注意：类" + className + " 不存在！");
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
            String modifiers = Modifier.toString(constructor.getModifiers());

            printWriter.print("\t");
            if (modifiers.length() > 0) {
                printWriter.print(modifiers + " ");
            }
            printWriter.print(name + "(");

            //打印参数列表
            Class[] parameter = constructor.getParameterTypes();
            for (int i = 0; i < parameter.length; ++i) {
                if (i > 0) {
                    printWriter.print(", ");
                }
                printWriter.print(parameter[i].getName());
            }
            printWriter.println(");");
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

            printWriter.print("\t");
            if (modifiers.length() > 0) {
                printWriter.print(modifiers + " ");
            }
            printWriter.print(returnType.getName() + " " + name + "(");

            //打印形参列表
            Class[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; ++i) {
                if (i > 0) {
                    printWriter.print(", ");
                }
                printWriter.print(parameterTypes[i].getName());
            }
            printWriter.println(");");
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

            printWriter.print("\t");
            if (modifiers.length() > 0) {
                printWriter.print(modifiers + " ");
            }
            printWriter.println(type.getName() + " " + name + ";");
        }
    }
}
