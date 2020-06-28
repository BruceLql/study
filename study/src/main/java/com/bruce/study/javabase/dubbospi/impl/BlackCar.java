package com.bruce.study.javabase.dubbospi.impl;
/*
 *@ClassName BlackCar
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/28 12:51
 *@Version 1.0
 */

import com.bruce.study.javabase.dubbospi.api.Car;
import org.apache.dubbo.common.URL;

public class BlackCar implements Car {

    @Override
    public void getColor(URL url) {
        System.out.println("Black Car");
    }
}