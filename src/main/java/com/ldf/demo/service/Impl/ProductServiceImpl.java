package com.ldf.demo.service.Impl;

import com.ldf.demo.mapper.ProductMapper;
import com.ldf.demo.pojo.Product;
import com.ldf.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/12 21:36
 * @code: 愿世间永无Bug!
 * @description:
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAll(String fuzzyName) {
        return productMapper.findAll(fuzzyName);
    }

    @Override
    public void deleteById(String id) {
        productMapper.deleteById(id);
    }

    @Override
    public Product findById(String id) {
        return productMapper.findById(id);
    }

    @Override
    public void productUpdate(Product product) {
        productMapper.productUpdate(product);
    }

    @Override
    public void save(Product product) {
        productMapper.save(product);
    }
}
