package com.bruce.study.listener;
/*
 *@ClassName FirstListener
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/25 15:52
 *@Version 1.0
 */

import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FirstListener implements ServletContextListener {

    public void contextDestroyed(ServletContextListener event) {

    }

    public void contextInitialized(ServletContextListener event) {
        System.out.println("linstener  ----- init ");
    }
}