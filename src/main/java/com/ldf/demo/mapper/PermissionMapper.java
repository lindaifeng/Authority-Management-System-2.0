package com.ldf.demo.mapper;

import com.ldf.demo.pojo.Permission;
import com.ldf.demo.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/14 22:27
 * @code: 愿世间永无Bug!
 * @description:
 */
@Mapper
@Repository
public interface PermissionMapper {
    List<Permission> findAll();

    void deletePermissionById(String permissionId);

    int deletePermissionByIdAndRole_Permission(String permissionId);

    List<Permission> findById(String id);

    List<Role> findByIdAndNotRole_Permission(String id);

    void deleteRoleById(@Param("permissionId") String permissionId, @Param("roleId") String roleId);

    void addRoleByPermissionId(@Param("permissionId") String permissionId, @Param("roleId") String roleId);

    void save(Permission permission);
}
