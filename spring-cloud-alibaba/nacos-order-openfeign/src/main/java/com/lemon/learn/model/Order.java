package com.lemon.learn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {

    // 主键
    private String id;

    // 用户编号
    private String userId;

    // 商品编号
    private String productId;

    // 商品名称
    private String productName;

    // 商品价格
    private Double productPrice;

    // 商品数量
    private Integer productCount;

    // 商品总价
    private Double productTotalPrice;

    // 订单状态
    private Integer orderStatus;

    // 订单创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderCreateTime;

    // 订单支付时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderPayTime;

    // 订单发货时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date orderDeliveryTime;

    // 订单支付渠道
    private String orderPayChannel;

    // 订单备注
    private String orderRemark;

}
