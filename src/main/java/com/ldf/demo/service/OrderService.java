package com.ldf.demo.service;

import com.ldf.demo.pojo.Orders;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 19:20
 * @code: 愿世间永无Bug!
 * @description:
 */
public interface OrderService {
    List<Orders> findAll(String fuzzyName);

    void deleteById( Integer id);

    Orders findById(Integer id);
}
