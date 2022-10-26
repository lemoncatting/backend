package com.lemon.learn.controller;

import com.alibaba.fastjson.JSONArray;
import com.lemon.learn.model.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/rabbit")
public class MessageController {

    @Resource
    private RabbitTemplate rabbitTemplate;



    @PostMapping("/direct")
    public String directMessage(@RequestBody String data){
        Message message = JSONArray.parseObject(data, Message.class);
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = message.getData();
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("messageId", messageId);
        messageMap.put("messageData", messageData);
        messageMap.put("createTime", createTime);
        ////将消息携带绑定键值：sendDirectRouting 发送到交换机sendDirectExchange
        rabbitTemplate.convertAndSend("directExchange", "directRouting", messageMap);
        System.out.println("发送成功 direct" + "\t" + messageMap);
        return "发送成功 direct" + "\t" + messageMap;
    }

    @PostMapping("/topic")
    public String topicMessage(@RequestBody String data){
        Message message = JSONArray.parseObject(data, Message.class);
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = message.getData();
        String topic = "";
        switch (message.getTopic()){
            case "man":
                topic = "topic.man";
                break;
            case "woman":
                topic = "topic.woman";
                break;
            case "total":
                topic = "topic.#";
                break;
            default:
                break;
        }
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("messageId", messageId);
        messageMap.put("messageData", messageData);
        messageMap.put("createTime", createTime);
        messageMap.put("topic", topic);
        rabbitTemplate.convertAndSend("topicExchange", topic, messageMap);
        System.out.println("发送成功" + "\t" + topic + "\t" + messageMap);
        return "发送成功" + "\t" + topic + "\t" + messageMap;
    }

    @PostMapping("/fanout")
    public String fanoutMessage(@RequestBody String data){
        Message message = JSONArray.parseObject(data, Message.class);
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = message.getData();
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("messageId", messageId);
        messageMap.put("messageData", messageData);
        messageMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, messageMap);
        System.out.println("发送成功 fanout" + "\t" + messageMap);
        return "发送成功" + "\t" + messageMap;
    }

    @PostMapping("/exchange")
    public String messageExchange(@RequestBody String data){
        Message message = JSONArray.parseObject(data, Message.class);
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = message.getData();
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("messageId", messageId);
        messageMap.put("messageData", messageData);
        messageMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange", "directRouting", messageMap);
        return "发送成功，但找不到交换机";
    }

    @PostMapping("/queue")
    public String messageQueue(@RequestBody String data){
        Message message = JSONArray.parseObject(data, Message.class);
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = message.getData();
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("messageId", messageId);
        messageMap.put("messageData", messageData);
        messageMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "directRouting", messageMap);
        return "发送成功，但找不到队列";
    }

}
