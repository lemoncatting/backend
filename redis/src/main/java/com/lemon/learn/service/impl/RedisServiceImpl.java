package com.lemon.learn.service.impl;

import com.lemon.learn.service.RedisService;
import com.lemon.learn.utis.RedisUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void redisString() {

        //添加值
        redisUtils.stringSet("String", "这是一个String类型的值");
        //取值
        Object value = redisUtils.stringGet("String");
        System.out.println(value);
    }

    @Override
    public void redisHash() {

        //添加值
        redisUtils.hashSet("Hash", "Hash", "这是一个Hash类型的值");
        //取值
        Object value1 = redisUtils.hashGet("Hash", "Hash");
        System.out.println(value1);
    }

    @Override
    public void redisSet() {
        //添值
        redisUtils.setSet("Set", "这是一组Set集合的第一个");
        redisUtils.setSet("Set", "这是一组Set集合的第二个");
        redisUtils.setSet("Set", "这是一组Set集合的第三个");
        //取值
        Set vaule = redisUtils.setGet("Set");
        System.out.println(vaule);
    }

    @Override
    public void redisList() {
        //添加值
        redisUtils.listSet("List", "这是一组List集合的第一个");
        redisUtils.listSet("List", "这是一组List集合的第二个");
        redisUtils.listSet("List", "这是一组List集合的第三个");
        //取值
        List list = redisUtils.listGet("List", 0, -1);
        System.out.println(list);
    }

    @Override
    public void redisSortedSet() {

    }
}
