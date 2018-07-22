/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: MessageConsumer
 * Author:   Kevin Xu
 * Date:     2018-03-19 下午 2:21
 * Description: 消息消费者
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.aexsharing.rabbitmq.attribute.priority.queue.java.client.consumer;

import com.rabbitmq.client.*;
import com.aexsharing.rabbitmq.attribute.priority.queue.java.client.utils.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈消息消费者〉
 *
 * @author Kevin Xu
 * @create 2018-03-19 
 * @since 1.0.0
 */
public class MessageConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = ChannelUtils.getChannel("RGP订单系统消息消费者");

        // 创建队列时设置队列最大优先级
        Map<String, Object> queueProperties = new HashMap<>();
        queueProperties.put("x-max-priority", 10);
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare("aex.order.add", true, false, false, queueProperties);
        channel.exchangeDeclare("aex.order", BuiltinExchangeType.DIRECT, true, false, false, new HashMap<>());
        channel.queueBind(declareOk.getQueue(), "aex.order", "add", new HashMap<>());

        // 消费aex.order.add队列
        channel.basicConsume(declareOk.getQueue(), false, "RGP订单系统ADD处理逻辑消费者", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(3000);
                    System.out.println("----------aex.order.add----------");
                    System.out.println(new String(body, "UTF-8"));
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                }
            }
        });
    }
}