package com.lemon.learn.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

//加@Configuration为全局
//@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    //设置超时时间
    //@Bean
    //public Request.Options options(){
    //    return new Request.Options(5000, 10000);
    //}

    //自定义拦截器
    //@Bean
    //public CustomFeignInterceptor customFeignInterceptor(){
    //    return new CustomFeignInterceptor();
    //}
}
