package com.lemon.learn.controller;

import com.lemon.learn.service.SeataAlibabaStockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/seata")
public class SeataAlibabaStockController {

    @Resource
    private SeataAlibabaStockService seataAlibabaStockService;

    @GetMapping("/reduce")
    public String reduce(){

        seataAlibabaStockService.reduce();
        return "扣除成功";
    }

}
