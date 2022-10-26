package com.lemon.learn.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    //绑定键
    public static final String MAN = "topic.man";
    public static final String WOMAN = "topic.woman";
    public static final String ALL = "topic.#";

    @Bean
    public Queue manQueue(){
        return new Queue(TopicConfig.MAN);
    }

    @Bean
    public Queue womanQueue(){
        return new Queue(TopicConfig.WOMAN);
    }

    @Bean
    public Queue allQueue(){
        return new Queue(TopicConfig.ALL);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    //将 manQueue 和 topicExchange 绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    public Binding manBindingExchange(){
        return BindingBuilder.bind(manQueue()).to(topicExchange()).with(MAN);
    }

    //将 womanQueue 和 topicExchange 绑定,而且绑定的键值为topic.woman
    //这样只要是消息携带的路由键是topic.woman,才会分发到该队列
    @Bean
    public Binding womanBindingExchange(){
        return BindingBuilder.bind(womanQueue()).to(topicExchange()).with(WOMAN);
    }

    //将 allQueue 和 topicExchange 绑定,而且绑定的键值为topic.#
    //这样只要是消息携带的路由键是topic.开头,才会分发到该队列
    @Bean
    public Binding allBindingExchange(){
        return BindingBuilder.bind(allQueue()).to(topicExchange()).with(ALL);
    }

}
