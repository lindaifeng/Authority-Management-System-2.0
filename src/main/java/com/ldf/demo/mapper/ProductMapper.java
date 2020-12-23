package com.ldf.demo.mapper;

import com.ldf.demo.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 清峰
 * @date: 2020/12/12 21:36
 * @code: 愿世间永无Bug!
 * @description:
 */
@Mapper
@Repository
public interface ProductMapper {

    List<Product> findAll(String fuzzyName);

    void deleteById(String id);

    Product findById(String id);

    void productUpdate(Product product);

    void save(Product product);
}
