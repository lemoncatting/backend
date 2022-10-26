package com.lemon.learn.controller;

import com.lemon.learn.feign.StockFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private StockFeignClient stockFeignClient;

    @GetMapping("/add")
    public String add(){
        return stockFeignClient.err();
    }

}
