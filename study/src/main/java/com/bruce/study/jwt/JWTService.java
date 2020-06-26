package com.bruce.study.jwt;

import com.bruce.study.filter.TokenAuthorFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 *@ClassName JWTService
 *@Description TODO
 *@Author Bruce
 *@Date 2020/6/27 1:35
 *@Version 1.0
 */
@Component
public class JWTService {
    private static Logger logger = LoggerFactory.getLogger(JWTService.class);

    /**
     * todo 用临近过期的token 换新token
     * @param token
     * @return String newToken
     */
    public String updateToken(String token){
        return "111";
    }
}