package com.bruce.study.javabase.dubbospi.impl;
/*
 *@ClassName Trucker
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/28 13:50
 *@Version 1.0
 */

import com.bruce.study.javabase.dubbospi.api.Car;
import com.bruce.study.javabase.dubbospi.api.Driver;
import org.apache.dubbo.common.URL;

public class Trucker implements Driver {
    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }


    @Override
    public void driverCar(URL url) {
        System.out.println(" aop 前置");
        car.getColor(url);
        System.out.println(" aop 后置");
    }
}