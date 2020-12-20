package com.ldf.demo.service;

import com.ldf.demo.pojo.Role;
import com.ldf.demo.pojo.UserInfo;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 22:05
 * @code: 愿世间永无Bug!
 * @description:
 */
public interface UserInfoService {
    List<UserInfo> findAll(String fuzzyName);

    UserInfo findById(String id);

    void deleteByIdRoleAndUser(String roleId, String userId);

    List<Role> findByIdNotRole(String id);

    void relatedRole(String roleId, String userId);

    UserInfo findByUsername(String username);
}
