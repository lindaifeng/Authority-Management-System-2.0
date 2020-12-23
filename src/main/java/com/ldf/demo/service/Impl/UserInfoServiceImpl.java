package com.ldf.demo.service.Impl;

import com.ldf.demo.mapper.UserInfoMapper;
import com.ldf.demo.pojo.Role;
import com.ldf.demo.pojo.UserInfo;
import com.ldf.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 22:05
 * @code: 愿世间永无Bug!
 * @description:
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> findAll(String fuzzyName) {
        return userInfoMapper.findAll(fuzzyName);
    }

    @Override
    public UserInfo findById(String id) {
        return userInfoMapper.findById(id);
    }

    @Override
    public void deleteByIdRoleAndUser(String roleId, String userId) {
        userInfoMapper.deleteByIdRoleAndUser(roleId,userId);
    }

    @Override
    public List<Role> findByIdNotRole(String id) {
        return userInfoMapper.findByIdNotRole(id);
    }

    @Override
    public void relatedRole(String roleId, String userId) {
        userInfoMapper.relatedRole(roleId,userId);
    }

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoMapper.findByUsername(username);
    }
}
