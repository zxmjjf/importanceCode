package com.book2.B2_Input_and_Output;

import java.io.*;

/**
 * @author jjf
 * @version 2019-09-27 1.0.1
 * 将对象保存到文件中
 */
public class B06_ObjcetStream {
    public static void main(String[] args) {
        try (FileOutputStream fileOut = new FileOutputStream("src/com/book2/B2_Input_and_Output/ObjectStream.dat");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOut);
             FileInputStream fileIn = new FileInputStream("src/com/book2/B2_Input_and_Output/ObjectStream.dat");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileIn)) {
            MyClass[] myClasses = new MyClass[5];
            for (int i = 0; i < 5; ++i) {
                myClasses[i] = new MyClass("jjf" + i, new Workdate(2019, 9, i));
            }

            //write myClass in file
            System.out.println("Write MyClass array in to files");
            objectOutputStream.writeObject(myClasses);
            for (MyClass myClass : myClasses) {
                System.out.println(myClass);
            }
            System.out.println();

            //read myClass on file
            System.out.println("read MyClass arrays on files");
            MyClass[] myClasses1 = (MyClass[])objectInputStream.readObject();
            for (MyClass myClass : myClasses1) {
                System.out.println(myClass);
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 支持对象系列化的类
 */
class MyClass implements Serializable {
    private static int number = 0;
    private int onlyID;
    private String name;
    private Workdate inWorkTime;

    MyClass(String name, Workdate workdate) {
        onlyID = ++number;
        this.name = name;
        this.inWorkTime = workdate;
    }

    MyClass(String name, int years, int months, int days) {
        this.name = name;
        this.inWorkTime = new Workdate(years, months, days);
    }

    public void setInWorkTime(Workdate workTime) {
        workTime.reSetTime(workTime);
    }

    public void setInWorkTime(int years, int months, int days) {
        inWorkTime.reSetTime(years, months, days);
    }

    @Override
    public String toString() {
        return "Work " + onlyID + " : " + name + ",\tEntry time: " + inWorkTime.toString();
    }

    /*private void readObject(ObjectInputStream inputStream) {

    }
    private void writeObject(ObjectOutputStream outputStream) {

    }*/
    @Override
    public MyClass clone() throws CloneNotSupportedException{
        try {
            PipedOutputStream pipedOutputStream = new PipedOutputStream();
            PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(pipedOutputStream)) {
                objectOutputStream.writeObject(this);
            }

            try (ObjectInputStream objectInputStream = new ObjectInputStream(pipedInputStream)) {
                return (MyClass)objectInputStream.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            CloneNotSupportedException cloneNotSupportedException = new CloneNotSupportedException();
            cloneNotSupportedException.initCause(e);
            throw cloneNotSupportedException;
        }
    }
}

/**
 * 存储工作日期
 */
class Workdate implements Serializable{
    private int years;
    private int months;
    private int days;

    Workdate(int yeas, int month, int days) {
        this.years = yeas;
        this.days = days;
        this.months = month;
    }

    Workdate(Workdate workdate) {
        this.years = workdate.years;
        this.months = workdate.months;
        this.days = workdate.days;
    }

    public void reSetTime(int years, int months, int days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }

    public void reSetTime(Workdate workdate) {
        this.years = workdate.years;
        this.months = workdate.months;
        this.days = workdate.days;
    }

    @Override
    public String toString() {
        return years + "-" + months + "-" + days;
    }
}

