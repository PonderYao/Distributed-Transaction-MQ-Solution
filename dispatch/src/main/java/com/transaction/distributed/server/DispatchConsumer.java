package com.transaction.distributed.server;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.transaction.distributed.entity.DispatchEntity;
import com.transaction.distributed.mapper.DispatchMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * DispatchConsumer.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/5/24 22:27
 */
@Component
public class DispatchConsumer {
    @Resource
    private DispatchMapper dispatchMapper;

    @RabbitListener(queues = "order_dic_queue")
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println("派单服务平台" + msg + ",消息id:" + messageId);
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String orderId = jsonObject.getString("orderId");
        if (StringUtils.isEmpty(orderId)) {
            // 日志记录
            return;
        }
        DispatchEntity dispatchEntity = new DispatchEntity();
        // 订单id
        dispatchEntity.setOrderId(orderId);
        // 外卖员id
        dispatchEntity.setTakeoutUserId(12L);

        try {
            int insertDistribute = dispatchMapper.insertDistribute(dispatchEntity);
            if (insertDistribute > 0) {
                // 手动签收消息,通知mq服务器端删除该消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // // 丢弃该消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

}
