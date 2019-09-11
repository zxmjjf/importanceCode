package knowledge;

/**此类介绍 instanceof 表达式的具体用法
 * 结构： 对象变量 instanceof 对象类
 * 注意：对象变量的类型分为声明类型和实际类型，实际类型只能为声明类型或声明类型的子类。
 * 结论：
     *  对象变量的声明类型与实际类型相同，且与instanceof 右边的数据类型相同，结果为true
     * 	对象变量的声明类型与实际类型不同，但声明类型与instanceof 右边的数据类型相同，结果为true
     * 	对象变量的声明类型与实际类型不同，但声明类型与instanceof 右边的数据类型相同，结果为true
     * 	instancof 右边的数据类型是左边对象变量的声明类型的父类，结果为true
     * 	instancof 右边的数据类型是左边对象变量的声明类型的父类，但却是对象变量的实际类型的祖先类，结果为true
     *
     *  如果对象变量的实际类型为父类本身，不能将其当作子类，结果为false
     * 	如果对象变量的实际类型为为其子类（相对声明类型）与instanceof 右边的类型一致，结果为true
     * 	如果对象变量的实际类型为其子类，instanceof 右边的类型为父类的其他不同的子类型，结果为false，即对象变量的实际类型为子类类型，instanceof 右边的类型也必须为相同的子类类型
 * 一句概括：
 *     只要 instanceof 左边对象变量的实际类型与右边的类类型相同，结果必为true，即此时不管对象变量的声明类型（正确的用法）
 *     只要 instance 右边的类类型为父类类型（相对左边），则结果必为true。不管左边的声明类型或实际类型如何。（糟糕的用法）
 *
 * 用法：intanceof 右边的数据类型如果是父类，或者祖先类，是一种十分糟糕的设计。而应该是具体的子类，相对右边对象变量来说
 *
 * */
public class Instanceof {
    public static void main(String[] args) {
        /**对象变量的类型为基类*/
        Parent parent1 = new Parent(); //实际类型与声明类型不同
        Parent parent2 = new Child1();  //实际类型为声明类型的子类1
        Parent parent3 = new Child2();  //实际类型为声明类型的子类2

        /**对象变量类型为子类（相对的）*/
        Child1 child1A = new Child1();
        //Child2 child2A = new Child1();
        //child1A = new Parent();
        Child2 child2A = new Child2_1();


        System.out.println("*******************\n当instanceof右边的类类型为基类是（即有子类时）\n***********************");
        if (parent1 instanceof Parent) {
            System.out.println("\t对象变量的声明类型与实际类型相同，且与instanceof 右边的数据类型相同，结果为true");
        }
        if (parent2 instanceof Parent) {
            System.out.println("\t对象变量的声明类型与实际类型不同，但声明类型与instanceof 右边的数据类型相同，结果为true");
        }
        if (parent3 instanceof Parent) {
            System.out.println("\t对象变量的声明类型与实际类型不同，但声明类型与instanceof 右边的数据类型相同，结果为true");
        }
        if (child1A instanceof Parent) {
            System.out.println("\tinstancof 右边的数据类型是左边对象变量的声明类型的父类，结果为true");
        }
        if (child2A instanceof Parent) {
            System.out.println("\tinstancof 右边的数据类型是左边对象变量的声明类型的父类，但却是对象变量的实际类型的祖先类，结果为true");
        }

        System.out.println("*******************\n当instanceof右边的类类型没有其他子类时\n***********************");
        if (parent1 instanceof Child1) {
            System.out.println("\t如果对象变量的实际类型为父类本身，能将其当作子类，结果为true");
        }else{
            System.out.println("\t如果对象变量的实际类型为父类本身，不能将其当作子类，结果为false");
        }
        if (parent2 instanceof Child1) {
            System.out.println("\t如果对象变量的实际类型为为其子类（相对声明类型）与instanceof 右边的类型一致，结果为true");
        }else{
            if ((Child1)parent2 instanceof Child1) {
                System.out.println("\t如果对象变量的实际类型为为其子类（相对声明类型）,能将其当作子类（即需强制转换才可以），结果为false");
            }
        }

        if (parent3 instanceof Child1) {
            System.out.println("\t如果对象变量的实际类型为其子类，instanceof 右边的类型为父类的其他不同的子类型，结果为true，" +
                    "只要对象变量的类型父类类型，不管实际类型如何，都可以当作任意子类类型判断");
        }else {
            System.out.println("\t如果对象变量的实际类型为其子类，instanceof 右边的类型为父类的其他不同的子类型，结果为false，" +
                    "即对象变量的实际类型为子类类型，instanceof 右边的类型也必须为相同的子类类型");
        }

        /**测试getClass方法*/
        System.out.println(parent1.getClass());
        System.out.println(parent2.getClass());
        System.out.println(parent3.getClass());
        System.out.println(child1A.getClass());
        System.out.println(child2A.getClass());
        /**运行结果表面，getClass方法返回对象变量的实际类型的全名，返回的是数据类型，而不是数据*/
        if (parent1.getClass() == parent2.getClass()) {
            System.out.println("不可能的事情发生了！！");
        }else {
            System.out.println("不可能的事情不会绝对不会发生！！");
        }


    }
}

class Parent{

}

class Child1 extends Parent{

}

class Child2 extends Parent{

}

class Child2_1 extends Child2{

}

