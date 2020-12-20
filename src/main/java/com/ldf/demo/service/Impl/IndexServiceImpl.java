package com.ldf.demo.service.Impl;

import com.ldf.demo.mapper.IndexMapper;
import com.ldf.demo.pojo.UserInfo;
import com.ldf.demo.service.IndexService;
import com.ldf.demo.utils.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 清峰
 * @date: 2020/12/12 21:42
 * @code: 愿世间永无Bug!
 * @description:
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IndexMapper indexMapper;
    @Override
    public UserInfo login(String username, String password) {
        return indexMapper.login(username, password);
    }
}
