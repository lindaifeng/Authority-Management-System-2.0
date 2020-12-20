package com.ldf.demo.interceptor;

import com.ldf.demo.pojo.Permission;
import com.ldf.demo.pojo.Role;
import com.ldf.demo.pojo.UserInfo;
import com.ldf.demo.service.UserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: 清峰
 * @date: 2020/12/15 20:51
 * @code: 愿世间永无Bug!
 * @description: 自定义shiro验证规则
 */
public class MyShiroRealm extends AuthorizingRealm{

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户认证
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("1：身份认证-->MyShiroRealm.doGetAuthorizationInfo()");
        //获取用户输入的信息
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        String userName=token.getUsername();
        String userPassword=new String(token.getPassword());


        //通过用户名去数据库查询数据并判断输入是否正确
        UserInfo user=userInfoService.findByUsername(userName);
        if(user==null){
            throw new UnknownAccountException("账号不存在！");
        }else if(!user.getPassword().equals(userPassword)){
            throw new IncorrectCredentialsException("密码不正确！");
        }

        //认证信息
        SimpleAuthenticationInfo authorizationInfo=new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        return authorizationInfo;
    }


    /**
     * 角色权限和对应权限添加
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户名

        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        String name = userInfo.getUsername();
        // 查询用户名称
        UserInfo user = userInfoService.findByUsername(name);
        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            // 添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            /*for (Permission permission : role.getPermissions()) {
                // 添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionName());
            }*/
        }
        return simpleAuthorizationInfo;
    }

}
