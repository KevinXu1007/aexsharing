/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SpringAMQPConsumerConfig
 * Author:   Kevin Xu
 * Date:     2018-03-16 下午 3:32
 * Description: SpringAMQP消费者配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.aexsharing.rabbitmq.spring.amqp.manual.declare.consumer.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈SpringAMQP消费者配置类〉
 *
 * @author Kevin Xu
 * @create 2018-03-16
 * @since 1.0.0
 */
@Configuration
public class SpringAMQPConsumerConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();

        // 配置连接信息
        connectionFactory.setHost("10.0.0.16");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("aex");
        connectionFactory.setPassword("aex");

        // 网络异常自动连接恢复
        connectionFactory.setAutomaticRecoveryEnabled(true);
        // 每10秒尝试重试连接一次
        connectionFactory.setNetworkRecoveryInterval(10000);

        // 设置ConnectionFactory属性信息
        Map<String, Object> connectionFactoryPropertiesMap = new HashMap();
        connectionFactoryPropertiesMap.put("principal", "aex");
        connectionFactoryPropertiesMap.put("description", "RGP订单系统V2.0");
        connectionFactoryPropertiesMap.put("emailAddress", "aex@foxmail.com");
        connectionFactory.setClientProperties(connectionFactoryPropertiesMap);

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory);
        messageListenerContainer.setQueueNames("aex.order.add");

        // 设置消费者线程数
        messageListenerContainer.setConcurrentConsumers(5);
        // 设置最大消费者线程数
        messageListenerContainer.setMaxConcurrentConsumers(10);

        // 设置消费者属性信息
        Map<String, Object> argumentMap = new HashMap();
        messageListenerContainer.setConsumerArguments(argumentMap);

        // 设置消费者标签
        messageListenerContainer.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String s) {
                return "RGP订单系统ADD处理逻辑消费者";
            }
        });

        // 使用setAutoStartup方法可以手动设置消息消费时机
        messageListenerContainer.setAutoStartup(false);

        // 使用setAfterReceivePostProcessors方法可以增加消息后置处理器
        //messageListenerContainer.setAfterReceivePostProcessors();

        messageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        messageListenerContainer.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) {
                try {
                    //Thread.sleep(20000);
                    //rabbitTemplate.convertAndSend("aex.order", "add", message);
                    System.out.println("***************************************************");
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                    System.out.println(new String(message.getBody(), "UTF-8"));
                    System.out.println(message.getMessageProperties());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return messageListenerContainer;
    }
}