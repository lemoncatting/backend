package com.lemon.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.lemon.learn.mapper")
public class NacosProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosProductApplication.class, args);
    }
}
