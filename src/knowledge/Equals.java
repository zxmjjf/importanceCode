package knowledge;

import java.util.Arrays;
import java.util.Objects;

/**
 * 测试Object类的 euqals()方法的实现原理
 * @author  jjf
 * @version 1.0.1  2019-07-16
 *
 * 结论：
 *      Object类中的equals方法默认比较的的是两个对象变量的引用值是否相等，即是否为同一变量；
 *
 * 所以：在使用equals方法时，如果调用的对象是自定义的类，而又没有重写equals方法，则比较的是引用值
 *       在使用由java类库中的类所产生的实例调用equals方法时，一定要查明该实例是否重写了equals方法；
 *
 * 注意1：String类中重写了equals方法，使其功能为：如果两个String实例变量的引用的内容相等，则结果为true
 *       数组类型数据虽然作为对象处理，但是没有为其重写Object类的equals方法，所以比较的是引用而不是数组
 *       的值，但可以是用Arrays 类的静态方法 Arrays.equals(Object[], Object[])来比较两个数组的元素是否相等。
 *       对于基本数据类型，必须先将其转化为类类型才可以调用equals方法，结果比较的是值是否相等。
 *
 * 注意2：如果是针对自定义的类，想要有自己的equals()方法逻辑，除了需要考虑需要实现值相等 or 引用相等外
 *        还有保证按照java规范的要求保留equals()方法的5个特性：
 *
 *          1.自反性：x.equals(x) 结果为true，这一般都会成立
 *          2.对称性：若x.equals(y)结果为true ，则 y.equals()结果也必须为true。这在于有继承关系的类模块中容易出错
 *                    即有继承关系的类模块，需要注意考虑子类之间是否equals()逻辑不同。如果是这往往要考虑对称性，
 *                    如果不是，则应该在父类将equals()方法声明为 final
 *          3.传递性：若x.equals(y)和x.equals(z)的结果都为true，则y.equals(z)的结果也为true
 *          4.一致性: 如果x和y对象没有改变，则x.equals(y)的结果始终不变。
 *          5.对于任意的非空引用x，x.equals(null)结果为false；
 */
public class Equals {
    private String name;
    private long iD;

    /**构造函数*/
    Equals(String name, long iD){
        this.name = name;
        this.iD = iD;
    }

    public static void main(String[] args) {
        Equals equals1 = new Equals("jjf", 02);
        Equals equals2 = new Equals("jjf", 02);
        String string1 = new String("jjf");
        String string2 = new String("jjf");
        int[] arrint1 = {1, 2, 3};
        int[] arrint2 = {1, 2, 3};
        Integer a = new Integer(3);
        int b = 3;


        System.out.println("self class equals: " + equals1.equals(equals2)); //false
        System.out.println("String equals " + string1.equals(string2)); //true
        System.out.println("arrays equals " + arrint1.equals(arrint2)); //false
        System.out.println("use Arrays.equals() 测试数组的相等性 " + Arrays.equals(arrint1, arrint2)); //true
        System.out.println("int equals " + ((Integer)a).equals(3)); //true

        System.out.println(Objects.equals(string1, string2)); //true
        System.out.println(Objects.equals(equals1, equals2)); //false

    }

}
