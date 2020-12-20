package com.ldf.demo.service;

import com.ldf.demo.pojo.Product;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/12 21:35
 * @code: 愿世间永无Bug!
 * @description:
 */
public interface ProductService {

    List<Product> findAll(String fuzzyName);

    void deleteById(String id);

    Product findById(String id);

    void productUpdate(Product product);

    void save(Product product);
}
