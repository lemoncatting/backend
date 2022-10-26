package com.lemon.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@RibbonClients(value ={
//    @RibbonClient(name = "stock-provider",configuration = RibbonRandomRuleConfig.class)
//})
public class NacosOrderRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosOrderRibbonApplication.class, args);
    }

}
