package com.lemon.learn.controller;

import com.lemon.learn.feign.ProductFeignClient;
import com.lemon.learn.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private ProductFeignClient productFeignClient;

    @GetMapping("/query/{productName}")
    public Product query(@PathVariable String productName){
        return productFeignClient.query(productName);
    }

}
