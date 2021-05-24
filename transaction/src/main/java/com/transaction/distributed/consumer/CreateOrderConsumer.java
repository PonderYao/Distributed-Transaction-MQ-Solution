package com.transaction.distributed.consumer;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.transaction.distributed.entity.OrderEntity;
import com.transaction.distributed.mapper.OrderMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * CreateOrderConsumer.java
 *
 * confirm机制保证订单服务投递的消息到达MQ
 * 手动ack机制保证派单服务不丢失MQ发过来的信息
 * MQ的补偿重试机制降低了因网络抖动的原因而订单----派单服务执行失败
 * 如果订单服务执行失败而回滚，那么补单消费者将补单重新插入一条数据进入订单表，保证派单服务能正确执行
 * 如果派单服务执行异常，那么派单消费者将补偿重试，仍旧失败那么将会放弃该消息并写入日志
 * 定期job对数和人工补偿，保证数据的一致性
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/5/24 22:03
 */
@Component
public class CreateOrderConsumer {
    @Resource
    private OrderMapper orderMapper;

    @RabbitListener(queues = "order_create_queue")
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println("补单消费者" + msg + ",消息id:" + messageId);
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String orderId = jsonObject.getString("orderId");
        // 判断订单是否存在，如果不存在 实现自动补单机制
        OrderEntity orderEntityResult = orderMapper.findOrderId(orderId);
        if (orderEntityResult != null) {
            System.out.println("订单已经存在 无需补单  orderId:" + orderId);
            return;
        }
        // 订单不存在 ，则需要进行补单

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setName("订单名称");
        // 价格是300元
        orderEntity.setOrderMoney(300d);
        Long commodityId = 30L;
        orderEntity.setOrderId(orderId);
        // ##################################################
        // 1.先下单，创建订单 (往订单数据库中插入一条数据)
        try {
            int orderResult = orderMapper.addOrder(orderEntity);
            System.out.println("orderResult:" + orderResult);
            if (orderResult >= 0) {
                // 手动签收消息,通知mq服务器端删除该消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            // 丢弃该消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }

    }
}