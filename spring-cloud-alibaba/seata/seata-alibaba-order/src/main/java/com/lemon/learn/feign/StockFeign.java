package com.lemon.learn.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "seata-alibaba-stock", path = "/seata")
public interface StockFeign {

    @GetMapping("/reduce")
    String reduce();
}
