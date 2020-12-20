package com.ldf.demo.interceptor;

import java.util.HashMap;
import java.util.Map;

import com.ldf.demo.interceptor.MyShiroRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class ShiroConfig {


    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     *
     * @return
     */

    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     *
     * @return
     */

    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        //记住我cookie生效时间 ,单位秒
        simpleCookie.setMaxAge(60*60*24);
        return simpleCookie;
    }


    @Bean
    public MyShiroRealm myShiroRealm(){
        System.out.println("2：myShiroRealm..........");
        MyShiroRealm myShiroRealm=new MyShiroRealm();
        return myShiroRealm;
    }


    // 权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        System.out.println("1：securityManager..........");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        //注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    // Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录
        shiroFilterFactoryBean.setLoginUrl("/");
        // 首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 错误页面，认证不通过跳转
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/failPage");

        Map<String, String> filterMap = new HashMap<String, String>();

        /** authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器
         * org.apache.shiro.web.filter.authc.FormAuthenticationFilter
            anon：它对应的过滤器里面是空的,什么都没做,可以理解为不拦截
         */

        //未认证可访问的权限 放行拦截资源
        filterMap.put("/webapp/**", "anon");
        filterMap.put("/*/**", "anon");
        filterMap.put("/**", "anon");
        // 已认证可访问的权限
        filterMap.put("/**", "authc");
        //配置记住我或认证通过可以访问的权限
        filterMap.put("/**", "user");
        // 注销(记住我状态可清除cookie)
        filterMap.put("/logout", "logout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //解决session丢失
        Map<String, Filter> fmap = new HashMap<>();
        fmap.put("addPrincipal", addPrincipalToSessionFilter());
        shiroFilterFactoryBean.setFilters(fmap);


        return shiroFilterFactoryBean;
    }

    /**
     * Shiro自定义过滤器（解决session丢失）
     * @return
     */
    @Bean
    public OncePerRequestFilter addPrincipalToSessionFilter() {
        return new AddPrincipalToSessionFilter();
    }

    // 加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}