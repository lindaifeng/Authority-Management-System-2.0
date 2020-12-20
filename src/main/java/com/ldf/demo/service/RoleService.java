package com.ldf.demo.service;

import com.ldf.demo.pojo.Permission;
import com.ldf.demo.pojo.Role;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 22:36
 * @code: 愿世间永无Bug!
 * @description:
 */
public interface RoleService {
    List<Role> findAll();

    int deleteByIdAndRole_User(String id);

    void deleteById(String id);

    void save(Role role);

    List<Role> findById(String id);

    void deleteByIdAndRole_PermissionId(String permissionId, String roleId);

    List<Permission> findRoleByIdAndNotPermission(String id);

    void addpermission(String permissionId, String roleId);
}
