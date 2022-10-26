package com.lemon.learn.controller;

import com.lemon.learn.model.Product;
import com.lemon.learn.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/query/{productName}")
    public Product query(@PathVariable("productName") String productName)  {


        return productService.query(productName);


    }
}
