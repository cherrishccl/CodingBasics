package com.boot.basics.coding.mq.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author cherrishccl
 * @Date 2020/7/28 14:43
 * @Version 1.0
 * @Description
 */
@Component
public class RabbitTopicProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send1(){
        rabbitTemplate.convertAndSend(RabbitTopicConfig.EXG_TOPIC, "topic.queue1", "13185018510581085");
    }

    public void send2(){
        rabbitTemplate.convertAndSend(RabbitTopicConfig.EXG_TOPIC, "topic.queue1", "13185018510581085");
    }
}
