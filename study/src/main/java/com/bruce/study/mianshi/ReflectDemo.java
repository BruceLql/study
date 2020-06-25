package com.bruce.study.mianshi;
/*
 *@ClassName ReflectDemo
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/21 18:01
 *@Version 1.0
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {

        Class<?> aClass = Class.forName("com.bruce.study.mianshi.Person");
        System.out.println("=======================");
        System.out.println(aClass);
        System.out.println("=======================");
//        Method[] declaredMethods = aClass.getDeclaredMethods();
//        Constructor<?> constructor = aClass.getConstructor();
        String name = aClass.getName();
        System.out.println("name："+name);
        Person o = (Person) aClass.newInstance();

        Method hello = aClass.getMethod("hello", String.class);
        hello.invoke(o,"bruce");

        o.setName("bruce");
        System.out.println(o.getName());

    }

}