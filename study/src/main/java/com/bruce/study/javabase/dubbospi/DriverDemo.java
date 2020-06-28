package com.bruce.study.javabase.dubbospi;
/*
 *@ClassName DriverDemo
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/28 13:48
 *@Version 1.0
 */

import com.bruce.study.javabase.dubbospi.api.Car;
import com.bruce.study.javabase.dubbospi.api.Driver;
import com.bruce.study.javabase.dubbospi.impl.Trucker;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.HashMap;
import java.util.Map;

public class DriverDemo {
    public static void main(String[] args) {
        ExtensionLoader<Driver> extensionLoader = ExtensionLoader.getExtensionLoader(Driver.class);

        Driver dirver = (Trucker) extensionLoader.getExtension("trucker");
        Map<String, String> map = new HashMap<>();
        map.put("carType","black");
        URL url = new URL("","",0,map);
        dirver.driverCar(url);

    }
}