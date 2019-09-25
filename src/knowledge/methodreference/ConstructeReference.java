package knowledge.methodreference;

/**         **主类**
 * 此类用于测试方法引用特特殊情况：构造器引用
 * Class::new
 * @author book1
 * @version 1.0.1 2019-07-28 10:00
 */
public class ConstructeReference {
    /**
     * 程序入口点
     * @param args
     */
    public static void main(String[] args) {
        test(Myclass::new, 3);

        test(number -> new Myclass(number), 3);

        test(new ConstructeInterface() {
            @Override
            public Myclass function(int number) {
                return new Myclass(number);
            }
        }, 3);
    }

    /**
     * 测试构造器引用，第一个参数为函数式接口类型
     * @param construnt
     * @param number
     */
    public static void test(ConstructeInterface construnt,int number) {
        Myclass myclass = construnt.function(number);
        System.out.println(myclass.toString());
    }
}



//----------------------------------------------------//
//----------------------------------------------------//

/**
 * 函数式接口，提供某种操作
 * @FunctionalInterface
 */
interface ConstructeInterface{
    Myclass function(int number);
}



//----------------------------------------------------------//
//----------------------------------------------------------//

/**
 * 构造器引用所所在得类
 */
class Myclass{
    private int munber;

    /**
     * 构造器
     * @param number
     */
    Myclass(int number){
        System.out.println("Myclass 构造器被调用");
        this.munber = number;
    }

    @Override
    public String toString() {
        return "in this object: number = " + munber + "";
    }
}


