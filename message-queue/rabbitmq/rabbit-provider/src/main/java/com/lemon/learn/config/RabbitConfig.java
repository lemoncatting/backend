package com.lemon.learn.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("ConfirmCallback:"  +  "\t" + "相关数据：" + correlationData);
            System.out.println("ConfirmCallback:"  +  "\t" + "确认情况：" + ack);
            System.out.println("ConfirmCallback:"  +  "\t" + "原因：" + cause);
        });

        rabbitTemplate.setReturnCallback((message, replyCode, relyCode, exchange, routingKey) -> {
            System.out.println("ReturnCallback:" + "\t" + "消息：" + message);
            System.out.println("ReturnCallback:" + "\t" + "回应码：" + replyCode);
            System.out.println("ReturnCallback:" + "\t" + "回应消息：" + relyCode);
            System.out.println("ReturnCallback:" + "\t" + "交换机：" + exchange);
            System.out.println("ReturnCallback:" + "\t" + "路由键：" + routingKey);
        });

        return rabbitTemplate;
    }
}
