package com.iflytek.shardingjdbcdemo.entity;

import lombok.Data;

@Data
public class Order {
    private Long orderId;
    private String orderName;
    private String status;
}
