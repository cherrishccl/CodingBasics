package com.boot.basics.coding.mq.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author cherrishccl
 * @Date 2020/8/6 11:41
 * @Version 1.0
 * @Description
 */
@Configuration
public class RabbitFanoutConfig {
    public static final String FANOUT_QUEUE1 = "fanout.queue1";
    public static final String FANOUT_QUEUE2 = "fanout.queue2";
    public static final String FANOUT_QUEUE3 = "fanout.queue3";
    public static final String FANOUT_EXCHANGE = "myFanoutExchange";

    /**
     *  创建三个队列 ：fanout.queue1   fanout.queue2  fanout.queue3
     *  将三个队列都绑定在交换机 myFanoutExchange 上
     *  因为是扇型交换机, 路由键无需配置,配置也不起作用
     */

    @Bean
    public Queue queueF1(){
        return new Queue(FANOUT_QUEUE1);
    }
    @Bean
    public Queue queueF2(){
        return new Queue(FANOUT_QUEUE2);
    }
    @Bean
    public Queue queueF3(){
        return new Queue(FANOUT_QUEUE3);
    }

    @Bean
    FanoutExchange myFanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    Binding bindingExchange1() {
        return BindingBuilder.bind(queueF1()).to(myFanoutExchange());
    }

    @Bean
    Binding bindingExchange2() {
        return BindingBuilder.bind(queueF2()).to(myFanoutExchange());
    }

    @Bean
    Binding bindingExchange3() {
        return BindingBuilder.bind(queueF3()).to(myFanoutExchange());
    }

}
