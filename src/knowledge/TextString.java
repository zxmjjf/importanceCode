package knowledge;
/**
 * 字符串变量的值只是一个引用变量，其指向两种字符串类型值：
 *      a) 字符串字面量：字符串字面量是共享的，即编译器会为java字符串字面量分配特殊的内存池
 *  将程序中所有出现的不同的字符串字面量保存，相同的字面量引用共享同一个字面量。
 *      b) 字符串对象：可以理解为值与字符串字面量一个性质，但，其机制上存于严格的堆内存上，即不会自动共享；
 *
 *  字符串对象的所有引用不会共享，一个对象一个地址，所以a3，a5，a6都互不相等！！
 *  字符串字面量进行+操作之后将不再是字符串字面量，所以，不会再共享
 *  substring方法所求的子字符串是母字符串，则，返回的结果是母字符串的地址。
 *
 *  结论：对于字符串变量的值，应先确定其是字符串字面量还是字符串对象。
 *        字符串字面量就相当于基本数据类型，字符串变量此时相当于不是对象变量（引用变量）
 *        字符串对象就是对象类型，字符串变量的值此时就是对象变量
 */


public class TextString {
    public static void main(String[] args) {
        String a1 = "aaa"; //字符串字面量；a1
        String a2 = "aaa"; // a1 与  a2 的值相同

        String a3 = new String(a1); //a3所对应的字符串的值不是字符串字面量，所以a1与a3的值不相等
        String a4 = a3; //a4 和 a3 的指向的地址值相同，且指向的都不是字符串字面量

        String a5 = new String("aaa"); //a5指向的也不是字符串字面量
        String a6 = new String(a3);



        if (a1 == "aaa") {
            System.out.println("共享字符串字面量: a1 " );
        }
        if (a1 == a2) {
            System.out.println("共享字符串字面量: a1 = a2" );
        }

        if (a1.equals(a3)) {
            System.out.println("a1 与 a3 的字符串的值相同" );
        }
        if (a1 == a3) {
            System.out.println("共享字符串字面量 ：a1 = a3" );
        }else{
            System.out.println("a1 与 a3 所指向的地址不用");
        }
        if (a4 == a3) {
            System.out.println("同一个字符串对象 ：a4 = a3" );
        }
        if (a4 == a1) {
            System.out.println("共享字符串字面量 ：a4 = a1" );
        }
        if (a6 == a3) {
            System.out.println("共享字符串字面量 ：a6 = a3" );
        }

        //字符串的连接和substring
        String b1 = "aa";
        String b2 = "aa";
        if (b1 == b2) {
            System.out.println("b1 = b2");
        }

        b1 = b2.substring(0,2); //如果 b2.substring(0,b2.length()),则此函数直接返回b2
        if (b1 == b2) {
            System.out.println("字符串字面量的substring运算结果可以是字符串字面量");
        } else {
            System.out.println("字符串字面量进行+操作之后将不再是字符串字面量");
        }

        b1 = b1 + "a";
        System.out.println(b1 + "\t" + a1);
        if (b1 == a1) {
            System.out.println("字符串字面量的+运算结果可以是字符串字面量");
        } else {
            System.out.println("字符串字面量进行+操作之后将不再是字符串字面量");
        }

        a2 += "";
        if (a2 == a1) {
            System.out.println("但字符串字面量 + 上空字符串，仍为字符串字面量"); //不成立语句
        }
    }
}
