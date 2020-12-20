package com.ldf.demo.controller;

import com.ldf.demo.pojo.UserInfo;
import com.ldf.demo.service.IndexService;
import com.ldf.demo.service.UserInfoService;
import com.ldf.demo.utils.Base64Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: 清峰
 * @date: 2020/12/12 9:31
 * @code: 愿世间永无Bug!
 * @description:
 */
@Controller
public class LoginController {
    @Autowired
    private UserInfoService userInfoService;



    /**
     * 退出的时候是get请求，主要是用于退出
     * @return
     */
    @GetMapping(value = "/")
    public String login(HttpSession session) {

        return "login";
    }


    @GetMapping(value = "/logout")
    public String logout() {
        return "logout";
    }

    /**
     * POST登录
     * @param
     * @return
     * @RequiresRoles("admin")
     * @RequiresPermissions("create")
     */
    @PostMapping(value = "/login")
    public String login(String username, String password,
                        @RequestParam(value = "rememberMe",required = false) boolean rememberMe,
                         HttpSession session, HttpServletRequest request) {


        System.out.println("dologin..........");
        try{
            AuthenticationToken token=new UsernamePasswordToken(username,Base64Utils.encode(password));
            //设置记住我
            ((UsernamePasswordToken) token).setRememberMe(rememberMe);
            //调用Shiro进行认证
            SecurityUtils.getSubject().login(token);
            //从Shiro中拿出User对象
            UserInfo user=(UserInfo)SecurityUtils.getSubject().getPrincipal();
            session.setAttribute("user",user);

            System.out.println("登录成功！");
        }catch (Exception e){
            request.getSession().setAttribute("msg","用户名密码错误");
            //登录失败，返回登录页面
            return "login";
        }
        //重定向到另一个控制器请求返回主页面
        return "redirect:/index";

    }



    @GetMapping("/index")
    public String index(HttpSession session){
        Object user = session.getAttribute("user");
        if (user==null){
            return "redirect:/";
        }
        System.out.println("登录成功");
        return "index";
    }


    @GetMapping("/failPage")
    public String failPage(){
        System.out.println("登录失败");
        return "failer";
    }

    @GetMapping("/403")
    public String My403(){
        System.out.println("权限不足");
        return "403";
    }






  /*  手动实现自定登录功能
  @GetMapping("/")
    public String login(HttpServletRequest request) {
        //判断用户是否设置了自动登录
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                if ("user".equals(cookie.getName())) {
                    //加密处理后要对其解密
                    String value = cookie.getValue();
                    String[] split = value.split("#");
                    System.out.println("截取的字符串为：" + split[0] + ": " + Base64Utils.decode(split[1]));
                    UserInfo userInfo = indexService.login(split[0],Base64Utils.decode(split[1]));
                    System.out.println("首次登陆查到cookie有数据：" + userInfo);
                    if (userInfo != null) {
                        return "index";
                    }
                    return "login";
                }
            }
        }
        return "login";
    }
*/

    /*@PostMapping("/login.do")
    public String postLogin(String username, String password, String remember,
                            HttpServletRequest request,
                            HttpServletResponse response){
        System.out.println(username+": "+password+": "+remember);
        UserInfo userInfo = indexService.login(username,Base64Utils.encode(password));
        System.out.println("查到用户："+userInfo);
        if (userInfo==null){
            request.getSession().setAttribute("msg","用户名密码错误,请重新输入");
            return "login";
        }
        if (remember!=null){
            //存入session用于拦截未登录用户请求
            request.getSession().setAttribute("user",userInfo.getUsername());
            //自动登录功能(下次访问携带该cookie取出数据查数据库有数据则登录)
            String user = userInfo.getUsername()+"#"+ Base64Utils.encode(userInfo.getPassword());
            //后期对密码进行加密处理
            Cookie cookie = new Cookie("user", user);
            cookie.setMaxAge(60*60*24*7);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        }
        return "index";
    }*/
//    @GetMapping("/logout.do")
//    public String logout(HttpServletRequest request,HttpServletResponse response){
//        request.getSession().removeAttribute("user");
//        Cookie cookie = new Cookie("user", "");
//        cookie.setMaxAge(0);
//        cookie.setPath("/");
//        response.addCookie(cookie);
//        return "redirect:/";
//    }

}
