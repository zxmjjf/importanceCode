package knowledge.methodreference;

/**
 * @author jjf 2019-07-27
 * @version 1.0.1
 * 描述：测试方法引用的三种情况
 */
public class MethodReference {
    /**
     * 此测试类所包含的两个私有操作数据
     */
     private int aInt, bInt;

    /**
     * 构造器
     * @param aInt
     * @param bInt
     */
    MethodReference(int aInt, int bInt) {
        this.aInt = aInt;
        this.bInt = bInt;
    }


    /**
     * 程序入口点
     * @param args
     */
    public static void main(String[] args) {
        MethodReference methodReference = new MethodReference(11, 22);
        //test1(methodReference);
        test2(methodReference);

    }

    /**
     * 测试第一种方法引用： object::method
     * @param methodReference
     * 结论：
     *  方法引用与lambda表示的转换： object::method ==> x -> object.method(x);
     * 所以：函数式接口中的方法的参数必须与method的参数完全匹配（参数的个数和类型）
     * 方法引用，lambda表达式，匿名内部类实现原理一样，可以相互转化，这些语句的编译器的最终结果都会解释成
     *          匿名内部类（对于匿名内部类编译器显然不需要转化
     * 其实：方法引用是lambda表达是的一种可能情况（一种简洁表示），被引用的方法充当 lambda表达式 -> 后面的的语句
     *      唯一的限制就是，所引用的方法必须与lambda表达式所对应的函数式接口的中的方法的参数匹配！！
     *
     *  这三种情况都不能避免一个过程：
     *          产生一个匿名对象：实现了函数式接口的类的对象。而，lambda表达式和方法引用的主体逻辑都是作为这个函数式接口
     *          的函数的方法体
     */
    public static void test1(MethodReference methodReference){
        System.out.println("the first method reference");
        Test1 test11 = new Test1();

        /*下面使用方法引用*/
        methodReference.add(test11::addNumber);
        /*test11::addNumber ==> x->test11.addNumber(x),要知道x表示函数式接口的参数即：addNumber(int avalues, int bvalues);
        * 中的两个参数：avalues， bvalues;
        * 此语句等价于下面：
        *   methodReference.add((avalues, bvalues) -> test11.addNumber(avalues, bvalues));
        * 也等同于下面：
        *   methodReference.add(new Test1Interface() {
            @Override
            public void addNumber(int avalues, int bvalues) {
                test11.addNumber(avalues, bvalues);
            }
        });
         * */
        methodReference.substract(test11::substractNumber);
        methodReference.add(test11::substractNumber);

        /**
        *   methodReference.add(test11::print);
        * 此语句会出错：print函数的原型为：print(), x的原型为：addNumber(int avalues, int bvalues)
        *
        * 所以x的参数会赋值对应给print(),即应该为：print(int avalues, int bvalues),然而并没有重载print方法
        * 此方法引用的编译器转换过程：
        * methodReference.add((avalues, bvalues) -> test11.print(avalues, bvalues));
        * 如果再将其转为内部类则为：
        * methodReference.add(new Test1Interface() {
        *   @Override
        *   public void addNumber(int avalues, int bvalues) {
        *       test11.print(avalues, bvalues);
        *   }
        *});
        *所以报错：方法参数不匹配！
        * */
        /**增加一个可以匹配的print函数子厚*/
        methodReference.add(Test1::print);
        /*上面语句调用了Test1类的静态方法*/

    }

    /**
     * 第二个测试方法
     * @param methodReference
     */
    public static void test2(MethodReference methodReference) {
        methodReference.add(Test2::print);
        methodReference.add((avalues, bvalues) -> System.out.println(avalues - bvalues));

    }


    /**
     * 可以使用方法引用的函数
     * @param test
     */
    public void add(Test1Interface test) {
        test.addNumber(aInt, bInt);
    }

    /**
     * 可以使用方法引用的函数
     * @param test
     */
    public void substract(Test1Interface test) {
        /*((Test1)test).substractNumber(aInt, bInt);*/
        test.addNumber(aInt, bInt);
    }


}

/**
 * 函数式接口
 * @FunctionalInterface
 */
interface Test1Interface {
    void addNumber(int avalues, int bvalues);

    /*int substractNumber(int avlues, int bvalues);*/
}

/**
 * 这个类用于建立实例，实现接口，通过此类的实例，使用方法引用语句
 */

class Test1 implements Test1Interface {
    @Override
    /**
     * 此方法覆盖TestInterface函数式接口的方法
     * @param avalues 形参
     * @param bvalues 形参
     */
    public void addNumber(int avalues, int bvalues) {
        System.out.println(avalues + " + " + bvalues + " = " + (avalues + bvalues));
    }

    /**
     * 此方法为Test1类的扩展方法
     * @param avalues
     * @param bvalues
     * @return
     */
    public void substractNumber(int avalues, int bvalues) {
        System.out.println(avalues + " - " + bvalues + " = " + (avalues - bvalues));
    }

    public void print(){
        System.out.println("无参数方法");
    }

    public static void print(int a, int b){
        System.out.println("aInt = " + a + "\tbInt = " + b);
    }
}


class Test2{
    public static void print(int aInt, int bInt) {
        System.out.println("In the Test2 class: the aInt = " + aInt + "\tbInt = " + bInt);
    }
}
