package com.lemon.learn.feign.fallback;

import com.lemon.learn.feign.StockFeignClient;
import org.springframework.stereotype.Component;

@Component
public class StockFeignFallback implements StockFeignClient {

    @Override
    public String err() {
        return "服务降级";
    }
}
