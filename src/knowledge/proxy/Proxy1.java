package knowledge.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;
/**
 * 通过两个test方法，了解代理proxy的工作原理
 * 总结：所谓代理类，它是在程序运行之后才形成的，它是一种类类型，可以代理一个或多个接口类，从而再需要这些类的方法的时候
 * 可以直接调用，代理类的方法是这些接口的所有方法和Object类方法，代理只有一个唯一的实例域，即：调用处理器
 */

/**
 * This programm demonstrates the use of proxies
 * 课本代码：P261
 * 代码清单6_10
 */

public class Proxy1 {
    public static void main(String[] args) {
        System.out.println("/****运行test1()****/");
        test1();
        System.out.println();
        System.out.println("/****运行test2()****/");
        test2();
    }

    /**第一个test方法：代理类所处理的数据类型是基本数据类型的对象类型*/
    public static void test1(){
        Object[] elements = new Object[1000]; //建立1000个元素的Object类型数组

        for (int i = 0; i < elements.length; ++i) {
            /**获得Integer类型的数据*/
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);/*构造调用处理器*/

            /**建立Compareble接口的代理实例，其唯一实例域为调用处理器handler*/
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            elements[i] = proxy; //用Object类型的数组保存代理实例
        }
        /**获得随机Integer值*/
        Integer key = new Random().nextInt(elements.length) + 1;

        /**查找Object数组中元素值是否等于key的元素，并返回该元素*/
        int result = Arrays.binarySearch(elements, key);

        /**如果存在，输出该元素*/
        if (result >= 0) {
            System.out.println(elements[result]);
            //为啥代理类的实例elements[result] 是一个整数？？？
        }
    }

    /**第二个test方法：代理类所处理的是自定义的类类型*/
    public static void test2(){
        Object[] elements = new Object[1000]; //建立1000个元素的Object类型数组

        for (int i = 0; i < elements.length; ++i) {
            /**获得自定义类型的数据*/
            MyClass value1 = new MyClass(i + 1);

            InvocationHandler handler = new TraceHandler(value1);/**构造调用处理器*/

            /**建立Compareble接口的代理实例，其唯一实例域为调用处理器handler*/
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            elements[i] = proxy; //用Object类型的数组保存代理实例
        }
        /**获得随机Integer值*/
        Integer key = new Random().nextInt(elements.length) + 1;

        /**查找Object数组中元素值是否等于key的元素，并返回该元素*/
        int result = Arrays.binarySearch(elements, key);

        /**如果存在，输出该元素*/
        if (result >= 0) {
            System.out.println(elements[result]);
            //为啥代理类的实例elements[result] 是一个整数？？？
        }
    }
}

/**为代理类绑定的对象处理器*/
class TraceHandler implements InvocationHandler {

    private Object target;

    public TraceHandler(Object target){
        this.target = target;
    }

    @Override
    /**此方法是使用代理的真正目的*/
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        System.out.print(target);
        System.out.print("." + method.getName() + "(");

        if (args != null) {
            for (int i = 0; i < args.length; ++i) {
                System.out.print(args[i]);
                if (i < args.length - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");
        /*Integer integer =  1;
        return integer;*/
        if (method.getName() == "compareTo"){
            return Integer.parseInt(target.toString()) - Integer.parseInt(args[0].toString());
        } else{
            return method.invoke(target, args);
        }

        //return method.invoke(target, args);

        /** return后面的语句才是真正的代理语句，代理的体现，注意此方法的第一个参数：target
         * target的意义是：代理类所正真代理的数据类型，如test1中的Integer数据类型，test2中的MyClass数据类型。
         */
    }
}

/**自定义的类*/
class MyClass /*implements Comparable*/{
    int value;

    MyClass(int value){
        this.value = value;
    }
    public String  toString(){
        return value + "";
    }

    /*@Override
    public int compareTo(Object o) {
        return value - Integer.parseInt(o.toString());
    }*/
}
