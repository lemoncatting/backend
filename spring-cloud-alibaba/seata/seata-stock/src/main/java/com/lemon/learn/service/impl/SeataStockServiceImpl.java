package com.lemon.learn.service.impl;

import com.lemon.learn.mapper.SeataStockMapper;
import com.lemon.learn.service.SeataStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SeataStockServiceImpl implements SeataStockService {

    @Resource
    private SeataStockMapper seataStockMapper;

    @Override
    public String  reduce() {
        seataStockMapper.reduce();
        return "扣除成功";
    }
}
