package com.bruce.study.filter;
/*
 *@ClassName FirstFilter
 *@Description 整合Filter 方式1
 *@Author Bruce
 *@Date 2020/6/25 15:31
 *@Version 1.0
 */

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(filterName = "FirstFilter" ,urlPatterns = {"*.do","*.jsp"})
@WebFilter(filterName = "FirstFilter" ,urlPatterns = "/first")
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入First Filter");
        chain.doFilter(request, response);
        System.out.println("离开First Filter");
    }

    @Override
    public void destroy() {

    }
}