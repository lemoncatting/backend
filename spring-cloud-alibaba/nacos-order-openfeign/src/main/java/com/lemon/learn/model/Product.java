package com.lemon.learn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Product implements Serializable {

    // 主键
    private String id;

    // 商品名称
    private String productName;

    // 商品价格
    private Double productPrice;

    // 商品库存
    private Integer productStock;

    // 商品描述
    private String productDescription;

    // 商品状态
    private Integer productStatus;

    // 创建人
    private String createUser;

    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    // 更新人
    private String updateUser;

    // 更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    // 商品类型
    private String productType;

    // 商品图片
    private String productImg;
}
