package com.lemon.learn.feign;

import com.lemon.learn.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nacos-product", path = "/product")
public interface ProductFeignClient {

    //SpringMVC注解
    @GetMapping("/query/{productName}")
    Product query(@PathVariable("productName") String productName);

    //feign原生注解
    //@RequestLine("GET /get/{id}")
    //String get(@Param("id") Integer id);
}
