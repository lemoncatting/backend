package com.lemon.learn.receiver;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DirectReceiver {
    @RabbitListener(queues = "direct")
    @RabbitHandler
    public void directReceiver(Map messageMap){
        System.out.println("directReceiver 消费者收到消息  :" + "\t" + messageMap);
    }
}
