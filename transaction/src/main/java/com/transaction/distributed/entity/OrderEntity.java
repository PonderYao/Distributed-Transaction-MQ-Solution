package com.transaction.distributed.entity;

import lombok.Data;

/**
 * OrderEntity.java
 * 订单实体
 * @author Ponder Yao
 * @version 1.0.0  2021/5/24 21:25
 */
@Data
public class OrderEntity {

    private Long id;
    // 订单名称
    private String name;
    // 下单金额
    private Double orderMoney;
    // 订单id
    private String orderId;

}
