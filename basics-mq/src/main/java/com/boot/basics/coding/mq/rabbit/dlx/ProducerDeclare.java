package com.boot.basics.coding.mq.rabbit.dlx;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author chencl
 * @Date 2020/10/29 18:24
 * @Version 1.0
 * @Description
 */
@Configuration
public class ProducerDeclare {
    @Bean
    public Queue userQueue(){
        return QueueBuilder.durable("user-queue")
                //声明该队列的死信消息发送到的 交换机 （队列添加了这个参数之后会自动与该交换机绑定，并设置路由键，不需要开发者手动设置)
                .withArgument("x-dead-letter-exchange", "common-dead-letter-exchange")
                //声明该队列死信消息在交换机的 路由键
                .withArgument("x-dead-letter-routing-key", "user-dead-letter-routing-key")
                .build();
    }
    @Bean
    public Exchange userExchange(){
        return ExchangeBuilder.topicExchange("user-exchange").durable(true).build();
    }

    @Bean
    public Binding userBinding(Queue userQueue, Exchange userExchange){
        return BindingBuilder.bind(userQueue).to(userExchange).with("user.*").noargs();
    }

    @Bean
    public Queue dlxQueue(){
        return QueueBuilder.durable("user-dead-letter-queue").build();
    }
    @Bean
    public Exchange dlxExchange(){
        return ExchangeBuilder.topicExchange("common-dead-letter-exchange").durable(true).build();
    }
    @Bean
    public Binding dlxBinding(Queue dlxQueue, Exchange dlxExchange){
        return BindingBuilder.bind(dlxQueue).to(dlxExchange).with("user-dead-letter-routing-key").noargs();
    }
}
