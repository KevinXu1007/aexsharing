/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: ConsumerApplication
 * Author:   Kevin Xu
 * Date:     2018-03-19 上午 10:35
 * Description: 消费者启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.aexsharing.rabbitmq.attribute.alternate.exchange.spring.amqp.consumer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 〈一句话功能简述〉<br>
 * 〈消费者启动类〉
 *
 * @author Kevin Xu
 * @create 2018-03-19
 * @since 1.0.0
 */
@ComponentScan(basePackages = "com.aexsharing.rabbitmq.attribute.alternate.exchange.spring.amqp.consumer")
public class ConsumerApplication {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ConsumerApplication.class);
    }
}