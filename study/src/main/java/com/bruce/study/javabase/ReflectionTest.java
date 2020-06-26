package com.bruce.study.javabase;
/*
 *@ClassName ReflectionTest
 *@Description 手写 List<String> 的动态代理类
 *@Author Bruce
 *@Date 2020/6/26 14:37
 *@Version 1.0
 */

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ReflectionTest {
    @Test
    public void test(){

        List<String> list = new ArrayList<String>();

        List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(list,args);
            }
        });

        proxyInstance.add("Hello");
        System.out.println(list);
    }


}