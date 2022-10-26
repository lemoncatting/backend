package com.lemon.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NacosOrderOpenfeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosOrderOpenfeignApplication.class, args);
    }

}
