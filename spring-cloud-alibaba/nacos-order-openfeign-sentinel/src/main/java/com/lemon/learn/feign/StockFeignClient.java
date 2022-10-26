package com.lemon.learn.feign;

import com.lemon.learn.feign.fallback.StockFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "nacos-stock", path = "/stock", fallback = StockFeignFallback.class)
public interface StockFeignClient {

    @GetMapping("/err")
    String err();

}
