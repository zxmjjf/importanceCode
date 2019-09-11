package knowledge.methodreference;

/**
 * 此类是为了测试方法引用的第三种情形：方法引用的方法所对应的类和函数式接口中唯一抽象方法中的形参数据类型相同。
 * 结论：如果函数式接口的第一个形参的数据类型和方法引用的数据类型相同，则，方法应用的类似于静态方法的使用，即
 * Class::method
 * 而不能
 * object::method
 * 因为该第一个实参（other1）会将调用转化为
 * other1.method
 *
 * e.g：
 * 函数是接口的抽象方法声明为： objectMethod(Test value1, Object...args);其中接口名为：MyInterfance
 * 现有Myclass的其中一个方法声明为：someMethod(Myinterfance interface, Object...args),则方法可以使用方法引用
 * 假设引用方法所属的类为：Test，其要用到的方法引用为 public void referencrMethod(Object...args)
 * 则可如下方法引用：假设：Mycalss myclass = new Myclass(Object...args);
 *      myclass.someMethod(Test::referenceMethod)
 * 最终，someMethod的第一个形参会转化成为隐式参数，即 ：value1.referenceMethod

 */
public class Test3MethRefe {
    /**
     * 程序入口点
     * @param args
     */
    public static void main(String[] args) {
        /**使用方法引用：注意虽然isEquals不是静态方法，但是此时方法引用也要使用
         * Test3::isEquals
         * 否则如果使用Test的对象类调用isEquals则会出错
         * 因为如果这样，则会解析成如下调用：
         * test::isEquals
        testforMethodReferece(test3::isEquals);
        ==》
        testforMethodReferece((other1, other12) -> test3.isEquals(other1, other12));
        ==》
        testforMethodReferece(new Test3Interface() {
            @Override
            public boolean judgeforTest3(Test3 other1, Test3 other12) {
                return test3.isEquals(other1, other12);
            }
        });
        */
        testforMethodReferece(Test3::isEquals);

        /**对应的lambda表达式*/
        /*
        testforMethodReferece((Test3 test31,  Test3 other) -> test31.isEquals(other));
         */
        testforMethodReferece((test31, other) -> test31.isEquals(other));

        /**对应的匿名内部类*/
        testforMethodReferece(new Test3Interface() {
            @Override
            public boolean judgeforTest3(Test3 test31, int other) {
                return test31.isEquals(other);
            }
        });
    }


    /**
     * 此方法用于测试方法引用：
     * @param methodInterface
     */
    public static void testforMethodReferece(Test3Interface methodInterface) {
        Test3 test1 = new Test3(1);
        Test3 test2 = new Test3(2);
        //System.out.println(methodInterface.judgeforTest3(test1, test2));
        System.out.println(methodInterface.judgeforTest3(test1, 2));

    }
}


//-------------------------------------------------------------------------//
//-------------------------------------------------------------------------//

/**
 * @FunctionalInterface
 * 函数式接口：注意唯一抽象方法的形参类型
 */
interface Test3Interface{
    /**
     * 函数式接口的唯一抽象方法：判断两个Test3类型对象是否相等（test.number的值是否相相等
     * @param first
     * @param second
     * @return
     */
    //boolean judgeforTest3(Test3 first, Test3 second);
    boolean judgeforTest3(Test3 first, int second);

}

//-------------------------------------------------------------------------//
//-------------------------------------------------------------------------//

/**
 * 此类类型，将作为方法引用的类对象类型，同时也刚好是函数式接口中唯一静态方法的两个参数的数类型。
 */
class Test3{
    private int number;

    /**
     * 构造器
     * @param number
     */
    Test3(int number) {
        this.number = number;
    }

    /**
     * 此方法作为函数式接口的方法引用实参
     * @param other
     * @return
     */
    public boolean isEquals(Test3 other) {
        return number == other.number;
    }

    /**
     * 重载
     * @param value
     * @return
     */
    public boolean isEquals(int value) {
        return number == value;
    }
}



