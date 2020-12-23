package com.ldf.demo.mapper;

import com.ldf.demo.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: 清峰
 * @date: 2020/12/12 21:43
 * @code: 愿世间永无Bug!
 * @description:
 */
@Mapper
@Repository
public interface IndexMapper {
    UserInfo login(String username, String password);
}
