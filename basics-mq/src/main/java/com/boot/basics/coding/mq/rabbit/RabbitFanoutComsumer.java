package com.boot.basics.coding.mq.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author cherrishccl
 * @Date 2020/8/6 11:54
 * @Version 1.0
 * @Description
 */
@Component
public class RabbitFanoutComsumer {
    @RabbitHandler
    @RabbitListener(queues = RabbitFanoutConfig.FANOUT_QUEUE1, containerFactory="rabbitListenerContainerFactory")
    public void receiveF1(String message){
        System.out.println("-------------------------");
        System.out.println("receiver1--->" + message);
        System.out.println("-------------------------");
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitFanoutConfig.FANOUT_QUEUE2, containerFactory="rabbitListenerContainerFactory")
    public void receiveF2(String message){
        System.out.println("-------------------------");
        System.out.println("receiver2--->" + message);
        System.out.println("-------------------------");
    }
    @RabbitHandler
    @RabbitListener(queues = RabbitFanoutConfig.FANOUT_QUEUE3, containerFactory="rabbitListenerContainerFactory")
    public void receiveF3(String message){
        System.out.println("-------------------------");
        System.out.println("receiver3--->" + message);
        System.out.println("-------------------------");
    }
}
