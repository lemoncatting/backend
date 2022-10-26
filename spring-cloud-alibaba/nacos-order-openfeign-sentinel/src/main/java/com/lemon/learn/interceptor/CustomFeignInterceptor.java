package com.lemon.learn.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomFeignInterceptor implements RequestInterceptor {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", "520");
        requestTemplate.query("name", "lemon");
        LOGGER.info("feign拦截器");
    }
}
