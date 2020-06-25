package com.bruce.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *@ClassName Hello
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/25 15:41
 *@Version 1.0
 */
@RestController
public class Hello {
    @RequestMapping("/first")
    public String first(){

        return "hello";
    }
    @RequestMapping("/second")
    public String second(){

        return "second";
    }
}