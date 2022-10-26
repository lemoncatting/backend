package com.lemon.learn.controller;

import com.alibaba.fastjson.JSONArray;
import com.lemon.learn.model.Message;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Queue;

@RestController
@RequestMapping("/active")
public class SendActiveController {

    @Resource
    //将sendQueue注入
    private Queue sendQueue;

    @Resource
    //Template中提供了发送接收的方法
    private JmsMessagingTemplate jmsMessagingTemplate;

    @PostMapping("/message")
    public String send(@RequestBody String data) {
        Message message = JSONArray.parseObject(data, Message.class);
        //向sendQueue队列发送消息
        jmsMessagingTemplate.convertAndSend(sendQueue, message.getData());
        System.out.println("发送成功" + "\t" + message.getData());
        return "发送成功" + "\t" + message.getData();
    }
}
