package knowledge.myselfLamda;

import java.util.Arrays;

/**
 * 设计自己的函数式接口替换为lambda表示式：
 *      1. lambda表达式一般用于替换函数式接口中的函数体的，所以，首先得设计一个函数式接口。
 *      2. 该接口作为某个方法的参数，且在此方法中，在某个部分代码调用该接口中的函数
 *      3. lambda表达式->后面表达式的计算结果是函数接口的返回值，其值也相当于实现该函数式接口
 *          的对象在实现该抽象方法时的ruturn语句（如果需要返回值）。
 *
 */
public class MyselfLambda implements MyselfInterface{
    @Override
    public  int bigVaules(int avalues, int bvaluse) {
        //System.out.println("调用了此方法");
        return avalues - bvaluse;
    }

    /*@Override
    public  static int smallVaules(int avalues, int bvaluse, int a) {
        return bvaluse - avalues;
    }*/

    public static void main(String[] args) {
        MyselfLambda myselfLambda = new MyselfLambda();

        /**排序方式1：通过方法调用，不使用lambda表达式*/
        int[] ints = {6, 3, 8, -2, 4, 0, 15, 99, 2, -32};
        System.out.println(Arrays.toString(ints));
        IntesSort.sort(ints, myselfLambda);
        System.out.println(Arrays.toString(ints));

        /**排序方式2：使用方法引用的lambda表达式*/
        int[] ints1 = {6, 3, 8, -2, 4, 0, 15, 99, 2, -32};
        System.out.println(Arrays.toString(ints1));
        IntesSort.sort(ints1, myselfLambda::bigVaules); //使用方法引用调用自己的lambda表达式
        System.out.println(Arrays.toString(ints1));

        /**排序方式3：使用非方法引用的方法lambda表示式*/
        int[] ints2 = {6, 3, 8, -2, 4, 0, 15, 99, 2, -32};
        System.out.println(Arrays.toString(ints2));
        IntesSort.sort(ints2, ((avalue, bvalus) -> avalue - bvalus)); //调用自己的lambda表达式
        System.out.println(Arrays.toString(ints2));

        /**排序方式四：使用非方法引用的方法lambda表示式*/
        int[] ints4 = {6, 3, 8, -2, 4, 0, 15, 99, 2, -32};
        System.out.println(Arrays.toString(ints4));
        IntesSort.sort(ints4,((avalues, bvaluse) -> myselfLambda.bigVaules(avalues, bvaluse))); //调用自己的lambda表达式
        System.out.println(Arrays.toString(ints4));

        /**
         * 以上程序的输出结果为：
         *      [6, 3, 8, -2, 4, 0, 15, 99, 2, -32]
         *      [-32, -2, 0, 2, 3, 4, 6, 8, 15, 99]
         *      [6, 3, 8, -2, 4, 0, 15, 99, 2, -32]
         *      [-32, -2, 0, 2, 3, 4, 6, 8, 15, 99]
         *      [6, 3, 8, -2, 4, 0, 15, 99, 2, -32]
         *      [-32, -2, 0, 2, 3, 4, 6, 8, 15, 99]
         *      [6, 3, 8, -2, 4, 0, 15, 99, 2, -32]
         *      [-32, -2, 0, 2, 3, 4, 6, 8, 15, 99]
         * 注意：
         *      其实这三种方式的底层实现原理都是一样的，即最终sort方法调用的都是实现了MyselfInterface接口中实现了
         *      bigVaules方法的对象。
         *      对于方法1和方法2，他们都是同一个实现了MyselfInterface接口的对象的同一个实例：myselfLambda中的bigVaules()
         *      方法，例如在这个方法的实现上加入一条打印语句：System.out.println("调用了此方法"); 则程序的输出结果中，
         *      前面的两次排序都会输出"调用了此方法",而最后一次排序不会。
         *      但在底层上，这三个方法原理相同，这以sort参数中需要对象和java中lambda表达式的语法实现规则有关，即
         *      在第三个方法中：
         *          先把sort函数的签名复制：void sort(int[] ints, MyselfInterface myselfInterface)
         *          如果第三个实参编译器识别的lambda表达式：则编译器会进行如下工作，
         *          1. 根据sort的方法签名中对应位置的形参类型MyselfInterface，实现这个MyselfInterface，再覆盖接口中的函数
         *          2. 覆盖函数的方法体即为lambda表达式中 -> 后面的语句，如果有返回值，则后面的语句表达式的计算结果即为
         *             返回值！很明显，实现了MyselfInterface接口的类与我们自定义的类MyselfLambda同，该类是编译器负责的，我们
         *             对其一无所知，我们只知道它能实现我们的要求就是了
         *
         * 所以：很明显，方法4和方法1，2一摸一样，方法4比方法2多余，方法2比方法1多余
         * 注意：
         *     1. java中提供lambda表达式的机制重点是解决程序设计是的代码冗余问题，即不应该因为只需要一个功能，就非得实现
         * 一个跟其他模块完全不相关的对象实例，从而造成代码复杂度和维护量和可读性加大的同时并没有增添一丝有用的东西，即
         * 即对于设计者来说，如果这部分的对象实例由虚拟机隐藏完成，则十分的有利
         *    2. 虽然再此例中的方法引用有点多余，且方法引用也是lambda表达式，但是，方法引用不是一无是处，当某个方法是静态
         *    方法时，方法引用可以不需要实现对象的实例，就可以调用现有的方法。
         */
    }
}
