package com.lemon.learn.controller;

import com.lemon.learn.service.SeataAlibabaOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/seata")
public class SeataAlibabaOrderController {

    @Resource
    private SeataAlibabaOrderService seataAlibabaOrderService;

    @GetMapping("/add")
    public String add(){

        return "下单成功" + seataAlibabaOrderService.create();
    }
}
