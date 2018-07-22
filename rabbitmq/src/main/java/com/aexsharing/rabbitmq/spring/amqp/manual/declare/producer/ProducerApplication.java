/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: ProducerApplication
 * Author:   Kevin Xu
 * Date:     2018-03-16 下午 3:37
 * Description:  生产者启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.aexsharing.rabbitmq.spring.amqp.manual.declare.producer;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.HashMap;

import static org.apache.http.client.protocol.ClientContext.ROUTE;

/**
 * 〈一句话功能简述〉<br> 
 * 〈 生产者启动类〉
 *
 * @author Kevin Xu
 * @create 2018-03-16 
 * @since 1.0.0
 */
@ComponentScan(basePackages = "com.aexsharing.rabbitmq.spring.amqp.manual.declare.producer")
public class ProducerApplication{

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProducerApplication.class);

        RabbitAdmin rabbitAdmin = context.getBean(RabbitAdmin.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
        RabbitTransactionManager transactionManager = new RabbitTransactionManager(context.getBean(ConnectionFactory.class));
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        // 声明交换机
        rabbitAdmin.declareExchange(new DirectExchange("aex.order", true, false, new HashMap<>()));

        // 声明消息 (消息体, 消息属性)
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setContentType("UTF-8");
        Message message = new Message("订单信息".getBytes(), messageProperties);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println(" 消息id:" + correlationData);
                if (ack) {
                    System.out.println("消息发送确认成功");
                } else {
                    System.out.println("消息发送确认失败:" + cause);

                }
            }
        });
        /*rabbitTemplate.setChannelTransacted(true);

        String result = transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus status) {
                rabbitTemplate.convertAndSend("aex.order", "add", message);
                return (String) rabbitTemplate.receiveAndConvert("aex.order.add");
            }
        });

        System.out.println(result);*/

        // 发布消息 (交换机名, Routing key, 消息);
        // 发布消息还可以使用rabbitTemplate.convertAndSend(); 其支持消息后置处理
        rabbitTemplate.convertAndSend("aex.order", "add", message);


    }
}