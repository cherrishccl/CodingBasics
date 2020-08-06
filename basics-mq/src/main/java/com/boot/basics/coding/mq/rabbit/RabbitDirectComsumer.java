package com.boot.basics.coding.mq.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author cherrishccl
 * @Date 2020/8/6 11:08
 * @Version 1.0
 * @Description
 */
@Component
@RabbitListener(queues = {RabbitDirectConfig.DIRECT_QUEUE})
public class RabbitDirectComsumer {
    @RabbitHandler
    public void process(String message){
        System.out.println("comsumer processing message: "+ message);
    }
}
