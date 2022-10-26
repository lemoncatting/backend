package com.lemon.learn.service.impl;

import com.lemon.learn.feign.StockFeign;
import com.lemon.learn.mapper.SeataAlibabaOrderMapper;
import com.lemon.learn.service.SeataAlibabaOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SeataAlibabaOrderServiceImpl implements SeataAlibabaOrderService {

    @Resource
    private SeataAlibabaOrderMapper seataAlibabaOrderMapper;

    @Resource
    private StockFeign stockFeign;

    @GlobalTransactional
    @Override
    public String create() {

        seataAlibabaOrderMapper.create();

        String msg = stockFeign.reduce();

        int a = 1/0;

        return msg + "8071";


    }
}
