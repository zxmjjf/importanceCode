package com.book2.B2_Input_and_Output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author book1
 * @version 1.0.1  2019-9-11
 * <descibe>通过反射，打印类的基本信息，并将类的信息输出到classInformations.txt文件中</descibe>
 */
public class PrintClassConstructor {
    public static PrintWriter printWriter;

    public static void main(String[] args) {
        String className = "java.util.zip.ZipEntry";
        try {
            String fileName = "src/com/book2/B2_Input_and_Output/_ZipData.txt";
            FileOutputStream fileOutputStream = new FileOutputStream(fileName, true);
            printWriter = new PrintWriter(fileOutputStream, true);

            printClass(className);



            fileOutputStream.flush();
            fileOutputStream.close();
            printWriter.close();

        } catch (IOException e ) {
            printWriter.println("文件处理异常");
        }


    }

    public static void printClass(String className) {
        try{
            Class name = Class.forName(className);
            /*Class superClass = name.getSuperclass();
            String modifiers = Modifier.toString(name.getModifiers());

            if (modifiers.length() > 0) {
                printWriter.print(modifiers + " ");
            }
            printWriter.print("class " + name.getName());
            if (superClass != null && superClass != Object.class) {
                printWriter.print(" extends " + superClass.getName());
            }*/
            /*printWriter.println("{\n\t//数据域");
            printFields(name);
            printWriter.println("\n\t//构造方法");*/
            printConstructors(name);
            /*printWriter.println("\n\t//方法");
            printMethods(name);
            printWriter.println("}\n");*/

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
            printWriter.print(sampleName(name) + "(");

            //打印参数列表
            Class[] parameter = constructor.getParameterTypes();
            for (int i = 0; i < parameter.length; ++i) {
                if (i > 0) {
                    printWriter.print(", ");
                }
                printWriter.print(sampleName(parameter[i].getName()));
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
            String modifiers = Modifier.toString(method.getModifiers());
            Class returnType = method.getReturnType();
            String name = method.getName();

            printWriter.print("\t");
            if (modifiers.length() > 0) {
                printWriter.print(modifiers + " ");
            }
            printWriter.print(sampleName(returnType.getName()) + " " + name + "(");

            //打印形参列表
            Class[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; ++i) {
                if (i > 0) {
                    printWriter.print(", ");
                }
                printWriter.print(sampleName(parameterTypes[i].getName()));
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
            printWriter.println(sampleName(type.getName()) + " " + name + ";");
        }
    }

    /**
     * <describe>将打印的类型名字的前缀去除</describe>
     * @param name
     * @return
     */
    private static String sampleName(String name) {
        int cur = name.lastIndexOf( '.' );
        String newName = name.substring( cur + 1);
        return newName;
    }
}
