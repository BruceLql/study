package com.bruce.study.filter;
/*
 *@ClassName SecondFilter
 *@Description 整合Filter 方式2
 *@Author Bruce
 *@Date 2020/6/25 15:45
 *@Version 1.0
 */

import javax.servlet.*;
import java.io.IOException;

public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入Second Filter");
        chain.doFilter(request, response);
        System.out.println("离开Second Filter");
    }

    @Override
    public void destroy() {

    }
}