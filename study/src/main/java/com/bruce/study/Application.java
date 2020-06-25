package com.bruce.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/*
 *@ClassName Application
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/11 6:10
 *@Version 1.0
 */
@SpringBootApplication
@ServletComponentScan  // 在启动时会扫描 @WebServlet、@WebFilter 注解，并将该类
public class Application {
    private final static Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        log.info("===============LoggerFactory==============");
    }
}