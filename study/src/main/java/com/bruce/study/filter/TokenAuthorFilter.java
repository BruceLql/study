package com.bruce.study.filter;
/*
 *@ClassName FirstFilter
 *@Description 整合Filter 方式1
 *@Author Bruce
 *@Date 2020/6/25 15:31
 *@Version 1.0
 */

import com.bruce.study.jwt.JWTService;
import com.bruce.study.util.JavaWebTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "FirstFilter" ,urlPatterns = {"*.do","*.jsp"})
@WebFilter(filterName = "TokenAuthorFilter", urlPatterns = "/app/*")
public class TokenAuthorFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(TokenAuthorFilter.class);
    @Autowired
    private JWTService jwtService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        rep.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        rep.setHeader("Access-Control-Allow-Methods", "POST, GET");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
//    rep.setHeader("Access-Control-Max-Age", "3600");
        rep.setHeader("Access-Control-Allow-Headers", "token");
//    rep.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type, Accept");


        rep.setCharacterEncoding("UTF-8");
        rep.setContentType("application/json; charset=utf-8");
        String token = req.getHeader("token");//header方式
        String method = ((HttpServletRequest) request).getMethod();


        if (method.equals("OPTIONS")) {
            rep.setStatus(HttpServletResponse.SC_OK);
        } else {

            if (null == token || token.isEmpty()) {
                logger.info("用户授权认证没有通过!客户端请求参数中无token信息");
                rep.setHeader("code", "100");
                rep.setHeader("msg", "用户授权认证没有通过!客户端请求参数中无token信息");
//                chain.doFilter(req, rep);
            } else {
                String exception = JavaWebTokenUtil.parseJWT(token).toString();
                if (exception != null) {
                    if (ExpiredJwtException.class.getName().equals(exception)) {
                        logger.info("token已过期！");
                        rep.setHeader("code", "100");
                        rep.setHeader("msg", "token is overtime！");
                        chain.doFilter(req, rep);
                    } else if (SignatureException.class.getName().equals(exception)) {
                        System.out.println("token sign解析失败");
                        rep.setHeader("code", "100");
                        rep.setHeader("msg", "token is invalid! ");
                        chain.doFilter(req, rep);
                    } else if (MalformedJwtException.class.getName().equals(exception)) {
                        System.out.println("token的head解析失败");
                        rep.setHeader("code", "100");
                        rep.setHeader("msg", "token is invalid! ");
                        chain.doFilter(req, rep);
                    } else if ("success".equals(exception)) {
                        logger.info("用户授权认证通过!");
                        rep.setHeader("code", "0");
                        chain.doFilter(req, rep);
                    } else {
                        logger.info("token的有效期小与2天！");
                        rep.setHeader("msg","token will invalid at 2 days！please refresh");
//                        String newToken = jwtService.updateToken(exception);
//                        logger.info("已生成新的token：" + newToken);
//                        rep.setHeader("newToken", newToken);
                        rep.setHeader("code", "1");
                        chain.doFilter(req, rep);
                    }
                }

            }

        }


    }

    @Override
    public void destroy() {

    }
}