/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: ProducerApplication
 * Author:   Kevin Xu
 * Date:     2018-03-19 上午 10:35
 * Description: 生产者启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.aexsharing.rabbitmq.attribute.alternate.exchange.spring.amqp.producer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈生产者启动类〉
 *
 * @author Kevin Xu
 * @create 2018-03-19
 * @since 1.0.0
 */
@ComponentScan(basePackages = "com.aexsharing.rabbitmq.attribute.alternate.exchange.spring.amqp.producer")
public class ProducerApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(com.aexsharing.rabbitmq.dependable.consumer.spring.amqp.producer.ProducerApplication.class);

        RabbitAdmin rabbitAdmin = context.getBean(RabbitAdmin.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);

        // 声明AE 类型为Fanout
        rabbitAdmin.declareExchange(new FanoutExchange("aex.order.failure", true, false, new HashMap<>()));

        // 为aex.order设置AE
        Map<String, Object> exchangeProperties = new HashMap<>();
        exchangeProperties.put("alternate-exchange", "aex.order.failure");
        rabbitAdmin.declareExchange(new DirectExchange("aex.order", true, false, exchangeProperties));

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setContentType("UTF-8");
        Message message = new Message("订单信息".getBytes(), messageProperties);
        rabbitTemplate.send("aex.order", "addXXX", message, new CorrelationData("201210704116"));
    }
}