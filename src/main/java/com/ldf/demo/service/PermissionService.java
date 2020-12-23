package com.ldf.demo.service;

import com.ldf.demo.pojo.Permission;
import com.ldf.demo.pojo.Role;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/14 22:28
 * @code: 愿世间永无Bug!
 * @description:
 */
public interface PermissionService {
    List<Permission> findAll();

    void deletePermissionById(String permissionId);

    int deletePermissionByIdAndRole_Permission(String permissionId);

    List<Permission> findById(String id);

    List<Role> findByIdAndNotRole_Permission(String id);

    void deleteRoleById(String permissionId, String roleId);

    void addRoleByPermissionId(String permissionId, String roleId);

    void save(Permission permission);
}
