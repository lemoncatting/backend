package com.lemon.learn.mapper;

import com.lemon.learn.model.Product;

public interface ProductMapper {


    Product query(String productName);
}
