/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RPCClient
 * Author:   Kevin Xu
 * Date:     2018-03-19 下午 7:09
 * Description: RPC服务调用端
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.aexsharing.rabbitmq.rpc.java.client.client;

import com.rabbitmq.client.*;
import com.aexsharing.rabbitmq.rpc.java.client.utils.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * 〈一句话功能简述〉<br>
 * 〈RPC服务调用端〉
 *
 * @author Kevin Xu
 * @create 2018-03-19
 * @since 1.0.0
 */
public class RPCClient {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = ChannelUtils.getChannel("RGP订单系统Client端");

        channel.queueDeclare("aex.order.add", true, false, false, new HashMap<>());
        channel.exchangeDeclare("aex.order", BuiltinExchangeType.DIRECT, true, false, new HashMap<>());
        channel.queueBind("aex.order.add", "aex.order", "add", new HashMap<>());

        String replyTo = "aex.order.add.replay";
        channel.queueDeclare(replyTo, true, false, false, new HashMap<>());
        String correlationId = UUID.randomUUID().toString();
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder().deliveryMode(2).contentType("UTF-8").correlationId(correlationId).replyTo(replyTo).build();
        channel.basicPublish("aex.order", "add", true, basicProperties, "订单消息信息".getBytes());
        channel.basicConsume("aex.order.add.replay", true, "RGP订单系统Client端", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("----------RPC调用结果----------");
                System.out.println(consumerTag);
                System.out.println("消息属性为:" + properties);
                System.out.println("消息内容为" + new String(body));
            }
        });
    }
}