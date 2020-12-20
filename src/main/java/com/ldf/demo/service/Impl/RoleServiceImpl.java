package com.ldf.demo.service.Impl;

import com.ldf.demo.mapper.RoleMapper;
import com.ldf.demo.pojo.Permission;
import com.ldf.demo.pojo.Role;
import com.ldf.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 22:37
 * @code: 愿世间永无Bug!
 * @description:
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public int deleteByIdAndRole_User(String id) {
        return roleMapper.deleteByIdAndRole_User(id);
    }

    @Override
    public void deleteById(String id) {
        roleMapper.deleteById(id);
    }

    @Override
    public void save(Role role) {
        roleMapper.save(role);
    }

    @Override
    public List<Role> findById(String id) {
        return roleMapper.findById(id);
    }

    @Override
    public void deleteByIdAndRole_PermissionId(String permissionId, String roleId) {
        roleMapper.deleteByIdAndRole_PermissionId(permissionId,roleId);
    }

    @Override
    public List<Permission> findRoleByIdAndNotPermission(String id) {
        return roleMapper.findRoleByIdAndNotPermission(id);
    }

    @Override
    public void addpermission(String permissionId, String roleId) {
        roleMapper.addpermission(permissionId,roleId);
    }
}
