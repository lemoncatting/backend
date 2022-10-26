package com.lemon.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.lemon.learn.mapper")
@EnableFeignClients
@EnableTransactionManagement
public class SeataAlibabaOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataAlibabaOrderApplication.class, args);
    }
}
