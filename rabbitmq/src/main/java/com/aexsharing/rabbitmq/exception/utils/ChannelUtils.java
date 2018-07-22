/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: ChannelUtils
 * Author:   Kevin Xu
 * Date:     2018/3/17 23:47
 * Description: RabbitMQ连接工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.aexsharing.rabbitmq.exception.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.impl.DefaultExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈RabbitMQ连接工具类〉
 *
 * @author Kevin Xu
 * @create 2018/3/17
 * @since 1.0.0
 */
public class ChannelUtils {
    public static Channel getChannelInstance(String connectionDescription) {
        try {
            ConnectionFactory connectionFactory = getConnectionFactory();
            Connection connection = connectionFactory.newConnection(connectionDescription);
            return connection.createChannel();
        } catch (Exception e) {
            throw new RuntimeException("获取Channel连接失败");
        }
    }

    private static ConnectionFactory getConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("10.0.0.16");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("aex");
        connectionFactory.setPassword("aex");

        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(10000);

        Map<String, Object> connectionFactoryPropertiesMap = new HashMap();
        connectionFactoryPropertiesMap.put("principal", "aex");
        connectionFactoryPropertiesMap.put("description", "RGP订单系统V2.0");
        connectionFactoryPropertiesMap.put("emailAddress", "aex@foxmail.com");
        connectionFactory.setClientProperties(connectionFactoryPropertiesMap);

        // 设置自定义异常处理器
        connectionFactory.setExceptionHandler(new DefaultExceptionHandler() {
            @Override
            public void handleConsumerException(Channel channel, Throwable exception, Consumer consumer, String consumerTag, String methodName) {
                System.out.println("----------消息消费异常处理----------");
                System.out.println("消息消费异常日志记录:" + exception.getMessage());
                super.handleConsumerException(channel, exception, consumer, consumerTag, methodName);
            }
        });
        return connectionFactory;
    }
}