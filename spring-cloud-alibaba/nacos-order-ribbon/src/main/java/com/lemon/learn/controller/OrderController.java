package com.lemon.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/add")
    public String add(){
        System.out.println("下单成功");
        return restTemplate.getForObject("http://nacos-stock/stock/reduce", String.class);
    }


}
