package com.ldf.demo.aspect;

import com.alibaba.druid.util.DruidWebUtils;
import com.ldf.demo.controller.SysLogController;
import com.ldf.demo.pojo.Syslog;
import com.ldf.demo.pojo.UserInfo;
import com.ldf.demo.service.SysLogService;
import com.ldf.demo.utils.Base64Utils;
import javafx.geometry.Pos;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 清峰
 * @date: 2020/12/15 11:43
 * @code: 愿世间永无Bug!
 * @description: private Date visitTime;//开始时间
 * private String visitTimeStr;
 * private String username;//当前用户名称
 * private String ip;//访问地址ip
 * private String url;//访问资源
 * private Long executionTime;//执行时长
 * private String method;//执行方法
 */
@org.aspectj.lang.annotation.Aspect
@Component
@SuppressWarnings("all")
public class Aspect {
    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//开始时间
    private String visitTimeStr;
    private String username;//当前用户名称
    private String ip;//访问地址ip
    private String url;//访问资源
    private Long executionTime;//执行时长
    private String method;//执行方法

    private Class clazz;
    long oldTime = 0;

    //    设置切面
    @Pointcut("execution(* com.ldf.demo.controller.*.*(..))")
    public void MyPointcut() {
    }

    //前置通知  主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("MyPointcut()")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是开始访问的时间

        clazz = jp.getTarget().getClass(); //具体要访问的类
        method = jp.getSignature().getName(); //获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问的方法的参数

    }

    //后置通知
    @After("MyPointcut()")
    public void doAfter(JoinPoint jp) throws Exception {
        String[] splitName = clazz.getName().split("\\.");
        String name = splitName[splitName.length - 1];

        //访问日志模块不记录到日志中
        if (!"SysLogController".equals(name)&&!"LoginController".equals(name)) {

            long time = new Date().getTime() - visitTime.getTime(); //获取访问的时长
            //获取访问的ip
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            ip = request.getRemoteAddr();
            url = request.getRequestURI();

           /* Subject subject = SecurityUtils.getSubject();
            UserInfo userInfo = (UserInfo) subject.getPrincipal();
            username = userInfo.getUsername();*/
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
            username = userInfo.getUsername();
         /*   username = (String) request.getSession().getAttribute("user");
            //用户是否设置了自动登录 从cookie中获取用户名
            if (username == null) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if ("user".equals(cookie.getName())) {
                            String value = cookie.getValue();
                            String[] split = value.split("#");
                            username = split[0];
                        }
                    }
                }
                //从cookies中拿到之后将用户名设置回去，不用重复遍历查询
                request.getSession().setAttribute("user", username);
            }*/


            //将日志相关信息封装到SysLog对象
            Syslog sysLog = new Syslog();
            sysLog.setExecutionTime(time); //执行时长
            sysLog.setIp(this.ip);
            sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method);
            sysLog.setUrl(url);
            sysLog.setUsername(this.username);
            sysLog.setVisitTime(visitTime);

            System.out.println(sysLog);
            //调用Service完成操作
            sysLogService.addSysLog(sysLog);
        }
    }
}





