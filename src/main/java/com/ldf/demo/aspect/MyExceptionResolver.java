package com.ldf.demo.aspect;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author: 清峰
 * @date: 2020/12/15 22:34
 * @code: 愿世间永无Bug!
 * @description:
 */

@ControllerAdvice
public class MyExceptionResolver {

    //认证失败
    @ExceptionHandler({AuthorizationException.class,IncorrectCredentialsException.class})
    public String handleShiroException(Exception ex) {
        ex.getStackTrace();
        return "failer";
    }

    //无权限
    @ExceptionHandler({UnauthorizedException.class})
    public String AuthorizationException(Exception ex) {
        ex.getStackTrace();
        return "403";
    }

}