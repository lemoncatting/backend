package com.lemon.learn.receiver;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FanoutReceiver {

    @RabbitListener(queues = "fanout.A")
    @RabbitHandler
    public void fanoutAReceiver(Map messageMap){
        System.out.println("FanoutReceiverA消费者收到消息  : " + messageMap.toString());
    }

    @RabbitListener(queues = "fanout.B")
    @RabbitHandler
    public void fanoutBReceiver(Map messageMap){
        System.out.println("FanoutReceiverB消费者收到消息  : " + messageMap.toString());
    }

    @RabbitListener(queues = "fanout.C")
    @RabbitHandler
    public void fanoutCReceiver(Map messageMap){
        System.out.println("FanoutReceiverC消费者收到消息  : " + messageMap.toString());
    }
}
