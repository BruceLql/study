package com.bruce.study.javabase.dubbospi;
/*
 *@ClassName CarDemo
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/28 12:54
 *@Version 1.0
 */

import com.bruce.study.javabase.dubbospi.api.Car;
import org.apache.dubbo.common.extension.ExtensionLoader;

public class CarDemo {
    public static void main(String[] args) {
        // 每个接口对应一个ExtensionLoader
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);

        Car redCar = extensionLoader.getExtension("balck"); // 配置文件里对应的key名称 red  true：默认值

        redCar.getColor(null);
    }
}