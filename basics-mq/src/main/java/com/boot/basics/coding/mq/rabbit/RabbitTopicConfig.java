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
    public final static String EXG_TOPIC = "ADD_CUSTOMER";
    @Bean
    public Queue queue1(){
        return new Queue("queue1");
    }
    @Bean
    public Queue queue2(){
        return new Queue("queue2");
    }
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXG_TOPIC);
    }

    /**
     * 将 queue1 和topicExchange绑定,而且绑定的键值为topic.queue1
     * 这样只要是消息携带的路由键是topic.queue1,才会分发到该队列
     * @return
     */
    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("topic.queue1");
    }

    /**
     * 将 queue2 和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
     * 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
     * @return
     */
    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("topic.#");
    }
}
