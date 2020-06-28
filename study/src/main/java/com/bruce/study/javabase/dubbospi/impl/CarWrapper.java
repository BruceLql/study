package com.bruce.study.javabase.dubbospi.impl;

import com.bruce.study.javabase.dubbospi.api.Car;
import org.apache.dubbo.common.URL;

/*
 *@ClassName CarWrapper
 *@Description 通过包装类 实现类似spring aop 功能
 *@Author Bruce
 *@Date 2020/6/28 13:42
 *@Version 1.0
 */
public class CarWrapper implements Car {
    private Car car;

    public void CarWrapper(Car car) {
        this.car = car;
    }


    @Override
    public void getColor(URL url) {
        // 实现类似 AOP 功能
        System.out.println( "before");
        car.getColor(url);
        System.out.println( "after");
    }
}