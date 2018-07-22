/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SpringAMQPServerConfig
 * Author:   Kevin Xu
 * Date:     2018-03-19 下午 7:31
 * Description: SpringAMQP Server端配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.aexsharing.rabbitmq.rpc.spring.amqp.server.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.aexsharing.rabbitmq.rpc.spring.amqp.server.rpc.RPCMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈SpringAMQP Server端配置〉
 *
 * @author Kevin Xu
 * @create 2018-03-19
 * @since 1.0.0
 */
@Configuration
public class SpringAMQPServerConfig {
    @Bean
    public CachingConnectionFactory connectionFactory() {
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
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);

        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(CachingConnectionFactory cachingConnectionFactory) {
        return new RabbitAdmin(cachingConnectionFactory);
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange("aex.order", true, false, new HashMap<>());
    }

    @Bean
    public Queue queue() {
        Queue queue = new Queue("aex.order.add", true, false, false, new HashMap<>());
        return queue;
    }

    @Bean
    public Binding binding() {
        Binding binding = new Binding("aex.order.add", Binding.DestinationType.QUEUE, "aex.order", "add", new HashMap<>());
        return binding;
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(CachingConnectionFactory cachingConnectionFactory) {
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(cachingConnectionFactory);
        messageListenerContainer.setQueueNames("aex.order.add");

        messageListenerContainer.setConcurrentConsumers(5);
        messageListenerContainer.setMaxConcurrentConsumers(10);

        Map<String, Object> argumentMap = new HashMap();
        messageListenerContainer.setConsumerArguments(argumentMap);
        messageListenerContainer.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String s) {
                return "RGP订单系统ADD处理逻辑消费者";
            }
        });

        RPCMethod rpcMethod = new RPCMethod();
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(rpcMethod);
        messageListenerAdapter.setDefaultListenerMethod("addOrder");

        messageListenerContainer.setMessageListener(messageListenerAdapter);
        return messageListenerContainer;
    }
}