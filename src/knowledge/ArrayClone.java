package knowledge;


import java.util.Arrays;

/**
 * @author book1 2019-07-27
 * @version 1.0.1
 * 描述：此类用于测试数组类型数据的clone()方法，即关于深浅拷贝
 * 结果：数组类型提供的克隆clone()方法，只能对数据进行最外层的浅克隆；
 */
public class ArrayClone {
    public static void main(String[] args) {
        /**两种数组*/
        int[] intarrays = {1, 2, 3, 4, 5};
        Myclass1[] myclass1s = new Myclass1[5];
        /*初始化myclass1s*/
        myclass1s[0] = new Myclass1(0, "book1");
        myclass1s[1] = new Myclass1(1, "zxm");
        myclass1s[2] = new Myclass1(2, "ljh");
        myclass1s[3] = new Myclass1(3, "lyj");
        myclass1s[4] = new Myclass1(4, "yld");

        /*声明两个拷贝数组*/
        int[] intsCopy = intarrays.clone();
        Myclass1[] myclass1s1Copy = myclass1s.clone();

        System.out.println("print intarrsys and myclass1");
        System.out.println(Arrays.toString(intarrays));
        System.out.println(Arrays.toString(myclass1s));

        System.out.println("\nprint there copy");
        System.out.println(Arrays.toString(intsCopy));
        System.out.println(Arrays.toString(myclass1s1Copy));

        /*change the copy values*/
        intsCopy[2] = 33;
        myclass1s1Copy[2].setiD(33);
        System.out.println("after change the values, the result is ");
        System.out.println(Arrays.toString(intarrays));
        System.out.println(Arrays.toString(myclass1s));
        System.out.println(Arrays.toString(intsCopy));
        System.out.println(Arrays.toString(myclass1s1Copy));

    }





}

class Myclass1 implements Cloneable{
    private int iD;
    private String name = "no-name";

    Myclass1(int id, String name) {
        this.iD = id;
        this.name = name;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return iD + " " + name;
    }

    @Override
    public Myclass1 clone() throws CloneNotSupportedException{
        return (Myclass1)super.clone();
    }
}

