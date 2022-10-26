package com.lemon.learn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/reduce")
    public String reduce(){
        System.out.println("扣除库存");
        return "扣除库存" + port;
    }

    @GetMapping("/err")
    public String err(){
        int a = 1/0;
        return "扣除库存" + port;
    }
}
