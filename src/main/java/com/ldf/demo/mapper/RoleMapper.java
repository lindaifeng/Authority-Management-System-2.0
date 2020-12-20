package com.ldf.demo.mapper;

import com.ldf.demo.pojo.Permission;
import com.ldf.demo.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 22:37
 * @code: 愿世间永无Bug!
 * @description:
 */
@Mapper
@Repository
public interface RoleMapper {
    List<Role> findAll();

    int deleteByIdAndRole_User(String id);

    void deleteById(String id);

    void save(Role role);

    List<Role> findById(String id);

    void deleteByIdAndRole_PermissionId(@Param("permissionId") String permissionId,@Param("roleId") String roleId);

    List<Permission> findRoleByIdAndNotPermission(String id);

    void addpermission(@Param("permissionId") String permissionId,@Param("roleId") String roleId);
}
