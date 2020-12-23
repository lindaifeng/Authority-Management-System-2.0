package com.ldf.demo.mapper;

import com.ldf.demo.pojo.Role;
import com.ldf.demo.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 22:06
 * @code: 愿世间永无Bug!
 * @description:
 */
@Mapper
@Repository
public interface UserInfoMapper {
    List<UserInfo> findAll(String fuzzyName);

    UserInfo findById(String id);

    void deleteByIdRoleAndUser(@Param("roleId") String roleId,@Param("userId") String userId);

    List<Role> findByIdNotRole(String id);

    void relatedRole(@Param("roleId") String roleId,@Param("userId") String userId);

    UserInfo findByUsername(String username);

}
