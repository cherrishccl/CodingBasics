package com.boot.basics.coding.mq.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author cherrishccl
 * @Date 2020/7/28 14:43
 * @Version 1.0
 * @Description
 */
@Component
public class RabbitTopicComsumer {

    @RabbitHandler
    @RabbitListener(queues = RabbitTopicConfig.TOPIC_QUEUE1, containerFactory="rabbitListenerContainerFactory")
    public void receiveT1(String message){
        System.out.println("-------------------------");
        System.out.println("receiver1--->" + message);
        System.out.println("-------------------------");
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitTopicConfig.TOPIC_QUEUE2, containerFactory="rabbitListenerContainerFactory")
    public void receiveT2(String message){
        System.out.println("-------------------------");
        System.out.println("receiver2--->" + message);
        System.out.println("-------------------------");
    }
}
