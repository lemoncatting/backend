package com.lemon.learn.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lemon.learn.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/flow")
    // @SentinelResource(value = "flow", blockHandler = "flowBlockHandler")
    public String flow() {
        return "正常访问";
    }

    public String flowBlockHandler(BlockException blockException){
        return "流控 " + blockException;
    }

    // 熔断
    @GetMapping("/flowThread")
    //@SentinelResource(value = "flowThread", blockHandler = "flowBlockHandler")
    public String flowThread() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "正常访问";
    }

    // 关联流控  访问add 触发get
    @GetMapping("/add")
    public String add(){
        return "生成订单";
    }

    // 关联流控  访问add 触发get
    @GetMapping("/get")
    public String get(){
        return "查询订单";
    }

    // 链路流控 入口 user
    @GetMapping("/test1")
    public String test1(){
        return orderService.getUser();
    }

    // 链路流控 入口 user
    @GetMapping("/test2")
    public String test2(){
        return orderService.getUser();
    }

    // 熔断  异常
    @GetMapping("/err")
    public String err(){
        int i = 1/0;
        return "error";
    }

    // 热点规则  必须使用SentinelResource设置
    @RequestMapping("/get/{id}")
    @SentinelResource(value = "getById",blockHandler = "HotBlockHandler")
    public String getById(@PathVariable("id") Integer id) {

        System.out.println("正常访问");
        return "正常访问";
    }

    public String HotBlockHandler(@PathVariable("id") Integer id,BlockException e) {
        return "热点异常处理";
    }

}
