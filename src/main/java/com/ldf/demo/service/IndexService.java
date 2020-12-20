package com.ldf.demo.service;

import com.ldf.demo.pojo.UserInfo;

/**
 * @author: 清峰
 * @date: 2020/12/12 21:42
 * @code: 愿世间永无Bug!
 * @description:
 */
public interface IndexService {
    UserInfo login(String username, String password);
}
