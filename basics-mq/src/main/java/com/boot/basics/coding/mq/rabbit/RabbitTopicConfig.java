package com.boot.basics.coding.mq.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author cherrishccl
 * @Date 2020/7/28 14:34
 * @Version 1.0
 * @Description topic
 */
@Configuration
public class RabbitTopicConfig {
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "myTopicExchange";

    @Bean
    public Queue queueT1(){
        return new Queue(TOPIC_QUEUE1);
    }
    @Bean
    public Queue queueT2(){
        return new Queue(TOPIC_QUEUE2);
    }
    @Bean
    public TopicExchange myTopicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /**
     * 将 queue1 和myTopicExchange绑定,而且绑定的键值为topic.queue1
     * 这样只要是消息携带的路由键是topic.queue1,才会分发到该队列
     * @return
     */
    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queueT1()).to(myTopicExchange()).with(TOPIC_QUEUE1);
    }

    /**
     * 将 queue2 和myTopicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
     * 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
     * @return
     */
    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queueT2()).to(myTopicExchange()).with("topic.#");
    }
}
