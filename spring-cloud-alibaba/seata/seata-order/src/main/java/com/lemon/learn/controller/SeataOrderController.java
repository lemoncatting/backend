package com.lemon.learn.controller;

import com.lemon.learn.service.SeataOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/seata")
public class SeataOrderController {

    @Resource
    private SeataOrderService seataOrderService;

    @GetMapping("/add")
    public String add(){

        return "下单成功" + seataOrderService.create();
    }
}
