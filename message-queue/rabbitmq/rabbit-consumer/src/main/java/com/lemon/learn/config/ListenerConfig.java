package com.lemon.learn.config;

import com.lemon.learn.receiver.AckReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class ListenerConfig {

    @Resource
    private CachingConnectionFactory connectionFactory;

    @Resource
    private AckReceiver ackReceiver;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // RabbitMQ 默认是自动确认，这里改为手动确认消息
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置一个队列
        //container.setQueueNames("direct");

        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
        container.setQueueNames("direct","fanout.A","fanout.B");

        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        //container.setQueues(new Queue("Direct1",true));
        //container.addQueues(new Queue("Direct2",true));
        //container.addQueues(new Queue("Direct3",true));
        container.setMessageListener(ackReceiver);
        return container;
    }
}
