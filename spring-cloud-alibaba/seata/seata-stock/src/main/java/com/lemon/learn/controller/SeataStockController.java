package com.lemon.learn.controller;

import com.lemon.learn.service.SeataStockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/seata")
public class SeataStockController {

    @Resource
    private SeataStockService seataStockService;

    @GetMapping("/reduce")
    public String reduce(){

        seataStockService.reduce();
        return "扣除成功";
    }

}
