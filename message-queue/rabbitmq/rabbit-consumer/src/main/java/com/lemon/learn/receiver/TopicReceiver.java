package com.lemon.learn.receiver;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TopicReceiver {

    @RabbitListener(queues = "topic.man")
    @RabbitHandler
    public void manTopicReceiver(Map messageMap) {
        System.out.println("manTopicReceiver 消费者收到消息  : " + messageMap.toString());
    }

    @RabbitListener(queues = "topic.woman")
    @RabbitHandler
    public void womanTopicReceiver(Map messageMap) {
        System.out.println("womanTopicReceiver 消费者收到消息  : " + messageMap.toString());
    }

    @RabbitListener(queues = "topic.#")
    @RabbitHandler
    public void totalTopicReceiver(Map messageMap) {
        System.out.println("totalTopicReceiver 消费者收到消息  : " + messageMap.toString());
    }
}
