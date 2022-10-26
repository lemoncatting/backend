package com.lemon.learn.service.impl;

import com.lemon.learn.mapper.SeataOrderMapper;
import com.lemon.learn.service.SeataOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class SeataOrderServiceImpl implements SeataOrderService {

    @Resource
    private SeataOrderMapper seataOrderMapper;

    @Resource
    private RestTemplate restTemplate;

    @Transactional
    @Override
    public String create() {

        seataOrderMapper.create();

        String msg = restTemplate.getForObject("http://localhost:8071/seata/reduce", String.class);

        int a = 1/0;

        return msg + "8071";


    }
}
