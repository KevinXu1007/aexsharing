/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: MessageProducer
 * Author:   Kevin Xu
 * Date:     2018-03-19 下午 2:21
 * Description: 消息生产者
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.aexsharing.rabbitmq.attribute.priority.queue.java.client.producer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.aexsharing.rabbitmq.attribute.priority.queue.java.client.utils.ChannelUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * 〈一句话功能简述〉<br>
 * 〈消息生产者〉
 *
 * @author Kevin Xu
 * @create 2018-03-19
 * @since 1.0.0
 */
public class MessageProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = ChannelUtils.getChannel("RGP订单系统消息生产者");
        channel.exchangeDeclare("aex.order", BuiltinExchangeType.DIRECT, true, false, false, new HashMap<>());
        for (int i = 1; i <= 10; i++) {
            // 发送消息时使用priority()方法指定消息优先级
            AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder().deliveryMode(2).contentType("UTF-8").priority(i).build();
            channel.basicPublish("aex.order", "add", true, basicProperties, ("订单消息信息" + i).getBytes());
        }
    }
}