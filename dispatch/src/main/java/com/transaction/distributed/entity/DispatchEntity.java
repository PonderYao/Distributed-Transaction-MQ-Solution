package com.transaction.distributed.entity;

import lombok.Data;

/**
 * DispatchEntity.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/5/24 22:18
 */
@Data
public class DispatchEntity {

    private Long id;
    // 订单号
    private String orderId;
    // 外卖员id
    private Long takeoutUserId;

}
