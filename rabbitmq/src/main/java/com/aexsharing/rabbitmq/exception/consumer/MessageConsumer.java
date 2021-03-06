/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: MessageConsumer
 * Author:   Kevin Xu
 * Date:     2018/3/17 23:46
 * Description: 消息消费者
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.aexsharing.rabbitmq.exception.consumer;

import com.rabbitmq.client.*;
import com.aexsharing.rabbitmq.exception.utils.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * 〈一句话功能简述〉<br>
 * 〈消息消费者〉
 *
 * @author Kevin Xu
 * @create 2018/3/17
 * @since 1.0.0
 */
public class MessageConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = ChannelUtils.getChannelInstance("RGP订单系统消息消费者");

        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare("aex.order.add", true, false, false, new HashMap<>());

        channel.exchangeDeclare("aex.order", BuiltinExchangeType.DIRECT, true, false, false, new HashMap<>());

        channel.queueBind(declareOk.getQueue(), "aex.order", "add", new HashMap<>());

        channel.basicConsume(declareOk.getQueue(), true, "RGP订单系统ADD处理逻辑消费者", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(consumerTag);
                System.out.println(envelope.toString());
                System.out.println(properties.toString());
                System.out.println("消息内容:" + new String(body));
                throw new RuntimeException("添加订单消息消费出现异常");
            }
        });
    }
}