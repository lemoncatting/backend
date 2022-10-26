package com.lemon.learn.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class BeanConfig {

    @Bean
    public Queue sendQueue(){
        return new ActiveMQQueue("sendQueue");
    }
}
