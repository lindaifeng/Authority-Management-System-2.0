/*
package com.ldf.demo.service.Impl;

import com.ldf.demo.mapper.UserInfoMapper;
import com.ldf.demo.pojo.Role;
import com.ldf.demo.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author: 清峰
 * @date: 2020/12/15 19:53
 * @code: 愿世间永无Bug!
 * @description:
 *//*

@Service("userDetailsService")
public class UserDetailServiceImpl implements  UserDetailsService {
    @Autowired
    private UserInfoMapper userInfoMapper;


    //实现UserDetailsService接口进行权限认证

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("用户登录了loadUserByUsername"+username);

        if (username==null||"".equals(username)){
            throw new RuntimeException("用户名不能为空！");
        }
        //根据用户名查询出用户信息（多表查询，角色和访问权限）
        UserInfo userInfo = userInfoMapper.findByUsername(username);
        if (userInfo==null){
            throw new UsernameNotFoundException(String.format("%s用户不存在",username));
        }
        //获取到角色封装到User类中做角色参数权限（有多个角色，需要自定义一个方法封装成UserDetails中，因UserDetails是接口所以用他的子类SimpleGrantedAuthority封装）
        List<Role> roles = userInfo.getRoles();
        //获取到所有角色后进行封装 权限格式为ROLE_XXX，是Spring Security默认的
        List<SimpleGrantedAuthority> authoritys = new ArrayList<>();
        for (Role role:roles){
            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        // 返回UserDetails实现类
        return new User(userInfo.getUsername(), userInfo.getPassword(),userInfo.getStatus() == 0 ? false : true,
                false,false,false, authoritys);
    }
}
*/
