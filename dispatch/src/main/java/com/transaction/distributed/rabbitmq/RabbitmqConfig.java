package com.transaction.distributed.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * RabbitmqConfig.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/5/24 22:20
 */
@Component
public class RabbitmqConfig {

    // 下单并且派单存队列
    private static final String ORDER_DIC_QUEUE = "order_dic_queue";
    // 补单队列，判断订单是否已经被创建
    public static final String ORDER_CREATE_QUEUE = "order_create_queue";
    // 下单并且派单交换机
    private static final String ORDER_EXCHANGE_NAME = "order_exchange_name";

    // 1.定义派单队列
    @Bean
    public Queue OrderDicQueue() {
        return new Queue(ORDER_DIC_QUEUE);
    }

    // 2.定义交换机
    @Bean
    DirectExchange directOrderExchange() {
        return new DirectExchange(ORDER_EXCHANGE_NAME);
    }

    // 3.订单队列与交换机绑定
    @Bean
    Binding bindingExchangeOrderDicQueue() {
        return BindingBuilder.bind(OrderDicQueue()).to(directOrderExchange()).with("orderRoutingKey");
    }

}
