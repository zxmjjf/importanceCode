package com.book1.A6proxy;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        //真实对象
        Subject realSubject =  new RealSubject();

        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(realSubject);
        //代理对象
        Object proxyClass = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Subject.class}, myInvocationHandler);
        testSubject(proxyClass);

    }

    public static void testSubject(Object subject) {
        /* 增加一个测试，保证方法能够运行*/
        if (subject instanceof Subject) {
            ((Subject)subject).sellBooks();
            ((Subject) subject).speak();
            System.out.println(subject.getClass());
        }
    }
}
