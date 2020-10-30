package com.boot.basics.coding.mq.rabbit.dlx;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * @Author chencl
 * @Date 2020/10/30 17:14
 * @Version 1.0
 * @Description
 */

@Configuration
public class ComsumerDeclare {
    /**
     * 正常用户队列消息监听消费者
     *
     * @param msg
     */
    @RabbitListener(queues = "user-queue")
    public void userConsumer(String msg) {
        System.out.println("正常用户业务监听：接收到消息: " + msg);
        throw new RuntimeException("模拟发生异常");
    }

    /**
     * @param msg
     */
    @RabbitListener(queues = "user-dead-letter-queue")
    public void userDeadLetterConsumer(String msg) {
        System.out.println("接收到死信消息并自动签收: " + msg);
    }
}
