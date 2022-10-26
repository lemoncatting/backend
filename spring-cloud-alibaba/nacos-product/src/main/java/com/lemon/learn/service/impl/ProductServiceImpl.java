package com.lemon.learn.service.impl;

import com.lemon.learn.mapper.ProductMapper;
import com.lemon.learn.model.Product;
import com.lemon.learn.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public Product query(String productName) {


        Product product = new Product();


        return productMapper.query(productName);
    }

}
