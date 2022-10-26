package com.lemon.learn.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lemon.learn.model.Result;
import com.lemon.learn.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    @SentinelResource(value = "getUser", blockHandler = "blockHandlerForGetUser")
    public String getUser() {
        return "查询用户";
    }

    public String blockHandlerForGetUser(BlockException blockException) {
        return new Result(100, "接口限流了", null).toString();
    }

}
