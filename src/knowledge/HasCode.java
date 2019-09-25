package knowledge;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author book1 2019-7-16  16:30
 * 功能：测试 Object类 中的 hasCode()方法，以及String类型数据的hasCode值
 *
 * 测试结论：
 *      1》Object类提供的hasCode()方法是针对对象，只有对象类型才能够调用，且只有当不同对象变量的值引用同一个类的实例
 *         时，hasCode的返回值才有可能相同，因为Object类提供的hasCode方法，返回的时实例再对中的地址值，而且对象
 *         在被创建时在堆中分配的地址是不可侧的，且与对象的构造时间顺序没有直接关系。
 *
 *      2》字符串对象和基本类型数据的对象类型才都覆盖类Object类的hasCode方法.参照测试结果分析
 *
 *      3》自定义的类可以可以覆盖Object类的hasCode()方法，但在规范上要看齐equals()方法，即两种应该同步
 *         因为这是java语法规程设计这两个方法的初衷和所希望的逻辑。
 *
 *      4》在使用java API中的类时，如果需要使用hasCode方法，应该了解其是否覆盖了Object类中的方法，即，
 *         搞清楚hasCode的返回值是如何产生的！
 *
 */
public class HasCode {
    public static void main(String[] args) {

        /**测试字符串类型的hanscode()*/
        String string1 = "jjfzxm";
        String string2 = "jjfzxm1";
        String string3 = new String(string1);
        System.out.println(string1.hashCode()); //-1159224311
        System.out.println(string2.hashCode()); //-1576215224
        System.out.println(Objects.hashCode(string3)); //-1159224311
        System.out.println(string1 == string3); //false
        /**
         * 结论：字符串重写了Object类的hascode算法，使得只要x.equals(y)结果为true，其hasCode()方法的返回值一定相同。
         */

        /**测试数组类型的hasCode()*/
        int[] arrint1 = {1, 2, 3, 4, 5};
        int[] arrint2 = {1, 2, 3, 4, 5};
        int[] arrint3 = {1, 2, 3, 4, 5,};
        int[] arrint4 = {1, 2, 3};
        int[] arrint5 = arrint1;
        System.out.println(arrint1.hashCode());     //257895351
        System.out.println(arrint2.hashCode()); //1929600551
        System.out.println(arrint3.hashCode()); //1690716179
        System.out.println(arrint4.hashCode()); //1053782781
        System.out.println(arrint5.hashCode());     //257895351

        /*使用Arrays.hasCode()方法，结果是累计数组元素的散列值*/
        System.out.println(Arrays.hashCode(arrint1));
        System.out.println(Arrays.hashCode(arrint4));
        System.out.println(Arrays.hashCode(arrint3));

        /**
         * 结论：数组变量是引用变量，只要引用的不是同一堆的数组，hasCode的返回值就不会相同
         *       数组类型并没有重写Object类的hasCode()方法；
         */

        /**测试基本类型的hasCode*/
        int aint = 3;
        Integer bint = 3;
        char a = 'c';
        Character character = 'c';
        System.out.println(((Integer)aint).hashCode()); //3
        System.out.println(Objects.hashCode(aint));
        System.out.println(bint.hashCode()); //3
        System.out.println(((Character) a).hashCode()); //99
        System.out.println(character.hashCode()); //99
        /**
         * 结论：基本数据类型都重写类Object类的hasCode()方法，调用hasCode的返回值为数值本身的大小。
         *      但，基本数据类型必须先转化为对象才可以使用hasCode()方法。
         */

        /**测试自定义类类型hasCode*/
        ClassA classA1 = new ClassA("book1");
        ClassA classA2 = new ClassA();
        ClassA classA3 = classA1;
        ClassA classA4 = new ClassA("zem");
        System.out.println(classA1.hashCode());     //2012232625
        System.out.println(classA2.hashCode()); //846063400
        System.out.println(classA3.hashCode());     //2012232625
        System.out.println(classA4.hashCode()); //627150481
        /**
         * 结论:自定义类类型的hasCode如果没有覆盖父类继承的hasCode(),则不同实例的hasCode返回值一定不同
         *      但，自定义的类完全可以重写hasCode方法，自定义hasCode操作。同时最好要在文件中显示注释实现过程
         */

    }
}

class ClassA{
    String string = "jjf1";
    ClassA(){}

    ClassA(String string) {
        this.string = string;
    }

    /**如果需要覆盖hasCode方法，下面是一种可行的设计例子。*/
    /*@Override
    public int hashCode() {
        return string.hashCode();
    }*/
}

