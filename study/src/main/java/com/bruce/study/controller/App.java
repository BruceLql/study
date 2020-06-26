package com.bruce.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *@ClassName App
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/27 1:56
 *@Version 1.0
 */
@RestController
@RequestMapping("/app")
public class App {

    @RequestMapping("hello")
    public String hello() {
        return "hello app";
    }
}