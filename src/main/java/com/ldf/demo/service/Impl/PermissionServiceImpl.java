package com.ldf.demo.service.Impl;

import com.ldf.demo.mapper.PermissionMapper;
import com.ldf.demo.pojo.Permission;
import com.ldf.demo.pojo.Role;
import com.ldf.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/14 22:28
 * @code: 愿世间永无Bug!
 * @description:
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public void deletePermissionById(String permissionId) {
        permissionMapper.deletePermissionById(permissionId);
    }

    @Override
    public int deletePermissionByIdAndRole_Permission(String permissionId) {
        return permissionMapper.deletePermissionByIdAndRole_Permission(permissionId);
    }

    @Override
    public List<Permission> findById(String id) {
        return permissionMapper.findById(id);
    }

    @Override
    public List<Role> findByIdAndNotRole_Permission(String id) {
        return permissionMapper.findByIdAndNotRole_Permission(id);
    }

    @Override
    public void deleteRoleById(String permissionId, String roleId) {
        permissionMapper.deleteRoleById(permissionId,roleId);
    }

    @Override
    public void addRoleByPermissionId(String permissionId, String roleId) {
        permissionMapper.addRoleByPermissionId(permissionId,roleId);
    }

    @Override
    public void save(Permission permission) {
        permissionMapper.save(permission);
    }
}
