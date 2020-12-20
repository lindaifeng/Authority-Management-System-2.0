package com.ldf.demo.service.Impl;

import com.ldf.demo.mapper.OrderMapper;
import com.ldf.demo.pojo.Orders;
import com.ldf.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/13 19:20
 * @code: 愿世间永无Bug!
 * @description:
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Orders> findAll(String fuzzyName) {
        return orderMapper.findAll(fuzzyName);
    }

    @Override
    public void deleteById( Integer id) {
        orderMapper.deleteByIdStr(id);
    }

    @Override
    public Orders findById(Integer id) {
        return orderMapper.findById(id);
    }
}
