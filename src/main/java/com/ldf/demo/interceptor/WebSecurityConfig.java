//package com.ldf.demo.interceptor;
//
//import com.ldf.demo.service.Impl.UserDetailServiceImpl;
//import com.ldf.demo.service.Impl.UserInfoServiceImpl;
//import com.ldf.demo.service.UserInfoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * @author: 清峰
// * @date: 2020/12/15 16:09
// * @code: 愿世间永无Bug!
// * @description:
// *
// */
//@Configuration //配置类
//@EnableWebSecurity //开启Security 服务
//@EnableGlobalMethodSecurity(prePostEnabled = true) //开启全局 Securtiy 注解
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//   @Autowired
//   private UserDetailServiceImpl userDetailsService;
//
//
//    //指定了密码的加密方式（5.0 版本强制要求设置）
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
//            //明文方式（不加密）
//            @Override
//            public String encode(CharSequence charSequence) {
//                return charSequence.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                return s.equals(charSequence.toString());
//            }
//        });
//       /* //如果你想要将密码加密，可以修改 configure() 方法如下：
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(new BCryptPasswordEncoder());*/
//    }
//    // 设置拦截忽略文件夹，可以对静态资源放行
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/*/**","../webapp/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                // 设置登陆页
//                .formLogin().loginPage("/")
//                .loginProcessingUrl("/login.do")
//                // 设置登陆成功页
//                .defaultSuccessUrl("/index").permitAll()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .permitAll()
//                // 异常处理
////                .failureUrl("/failPage").permitAll()
//                .and()
//                //注销功能
//                .logout().permitAll();
////                .and()
//                //实现记住我功能(前端命名必须是remember-me)
////                .rememberMe();
//        // 关闭CSRF跨域(关闭防火墙)
//        http.csrf().disable();
//    }
//}
