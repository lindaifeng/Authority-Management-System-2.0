package com.ldf.demo.mapper;

import com.ldf.demo.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 19:19
 * @code: 愿世间永无Bug!
 * @description:
 */
@Mapper
@Repository
public interface OrderMapper {
    List<Orders> findAll(String fuzzyName);

    void deleteByIdStr(Integer id);

    Orders findById(Integer id);
}
