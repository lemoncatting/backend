package com.lemon.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ReceiveActiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiveActiveApplication.class, args);
    }
}
