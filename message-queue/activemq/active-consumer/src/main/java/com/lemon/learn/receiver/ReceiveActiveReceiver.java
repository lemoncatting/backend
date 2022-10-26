package com.lemon.learn.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ReceiveActiveReceiver {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;


    //使用JmsListener注解配置监听的队列
    @JmsListener(destination = "sendQueue")
    public void handleMessage(String data){
        System.out.println("成功接收data" + "\t" + data);
    }
}
