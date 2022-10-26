package com.lemon.learn.controller;

import com.lemon.learn.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisService redisService;

    @GetMapping("/redisString")
    public String redisString() {
        this.redisService.redisString();
        return "String";
    }

    @GetMapping("/redisHash")
    public String redisHash() {
        this.redisService.redisHash();
        return "Hash";
    }

    @GetMapping("/redisSet")
    public String redisSet() {
        this.redisService.redisSet();
        return "Set";
    }

    @GetMapping("/redisList")
    public String redisList() {
        this.redisService.redisList();
        return "List";
    }

    @GetMapping("/redisSortedSet")
    public void redisSortedSet() {
        //有序的set，故而省略
    }
}
