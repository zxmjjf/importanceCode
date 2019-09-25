package knowledge.myselfLamda;

/**
 * @author book1 2019-07-27 15:02
 * @version 1.0.1
 * 描述：获得lambda表达式产生的内部类，再通过此内部类调用其方法
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        int a = 1, b = 3;
        MyselfInterface myselfInterface = (avalues, bvalues) -> avalues + bvalues;
        System.out.println(myselfInterface.bigVaules(a, b));
        A a1 = new C();
        Object object = new C();
        System.out.println(a1);
        Lamdaparent lamdaparent = (values, bvalues) -> values + bvalues;
    }
}

class A{
    @Override
    public String toString() {
        return "Aclass";
    }
}

class B extends A{
    @Override
    public String toString() {
        return "Bclass";
    }
}

class C extends  B{
    @Override
    public String toString() {
        return "Cclass";
    }
}
