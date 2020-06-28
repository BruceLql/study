package com.bruce.study.javabase.dubbospi.api;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI(value = "black")
public interface Car {

    @Adaptive(value = "carType")
    void getColor(URL url);
}
