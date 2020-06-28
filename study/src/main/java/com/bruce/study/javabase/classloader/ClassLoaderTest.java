package com.bruce.study.javabase.classloader;

import org.springframework.http.converter.json.GsonBuilderUtils;

/*
 *@ClassName ClassLoaderTest
 *@Description 类加载过程顺序
 *@Author Bruce
 *@Date 2020/6/28 1:03
 *@Version 1.0
 */
public class ClassLoaderTest {
    /*
    类加载过程顺序:
        1、父类的静态变量
        2、父类的静态代码块
        3、子类的静态变量
        4、子类的静态代码块
        5、父类的非静态变量
        6、父类的非静态代码块
        7、父类的构造方法
        8、子类的非静态变量
        9、子类的非静态代码块
        10、子类的构造方法
    * */
    public static void main(String[] args) {
        B b = new B();
    }
}

class A{

    static String str1 = "父类A的静态变量";

    String str2 = "父类A的非静态变量";

    static {
        System.out.println("1.执行了父类A的静态代码块 str1:"+str1 );
    }

    {
        System.out.println("3.执行了父类A的非静态代码块 str2:"+str2);
    }

    public A(){
        System.out.println("4.执行了父类A的构造方法");
    }
}

class B extends A{

    static String str1 = "子类B的静态变量";
    String str2 = "子类B的非静态变量";

    static {
        System.out.println("2.执行了子类B的静态代码块 str1:"+str1 );
    }

    {
        System.out.println("5.执行了子类B的非静态代码块 str2:"+str2 );
    }

    public B(){
        System.out.println("6.执行了子类B的构造方法");
    }
}