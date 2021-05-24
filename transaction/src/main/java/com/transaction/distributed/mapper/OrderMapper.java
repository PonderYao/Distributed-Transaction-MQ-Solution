package com.transaction.distributed.mapper;

import org.apache.ibatis.annotations.*;
import com.transaction.distributed.entity.OrderEntity;

/**
 * OrderMapper.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/5/24 21:26
 */
@Mapper
public interface OrderMapper {

    @Insert(value = "INSERT INTO `order_info` VALUES (#{id}, #{name}, #{orderMoney},#{orderId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int addOrder(OrderEntity orderEntity);

    @Select("SELECT id as id ,name as name , order_money as orderMoney,orderId as orderId from order_info where orderId=#{orderId};")
    public OrderEntity findOrderId(@Param("orderId") String orderId);

}