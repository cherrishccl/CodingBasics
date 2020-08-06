package com.boot.basics.coding.mq.rabbit.service;

import com.boot.basics.coding.mq.rabbit.RabbitDirectConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author cherrishccl
 * @Date 2020/8/6 10:17
 * @Version 1.0
 * @Description
 */
@Service
public class RabbitService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    static class NamedThreadFactory implements ThreadFactory{

        private final AtomicInteger poolNumber = new AtomicInteger(1);

        private final ThreadGroup threadGroup;

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        public  final String namePrefix;

        NamedThreadFactory(String name){
            SecurityManager s = System.getSecurityManager();
            threadGroup = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            if (null==name || "".equals(name.trim())){
                name = "pool";
            }
            namePrefix = name +"-"+
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(threadGroup, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    public RabbitService() {
    }

    public String sendReq(){

        /*ExecutorService executorService = new ThreadPoolExecutor(2,5,1,
                TimeUnit.MINUTES, new LinkedBlockingDeque<>(),new NamedThreadFactory("RabbitMQ-Send"));

        for(int i = 0; i < 100; i++){
            executorService.execute(() -> {
                //将消息携带绑定键值：myDirectRouting 发送到交换机 myDirectExchange
                rabbitTemplate.convertAndSend(RabbitDirectConfig.DIRECT_EXCHANGE,
                        RabbitDirectConfig.DIRECT_ROUTING, Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
         */
        AtomicInteger num = new AtomicInteger();
        for(int i = 0; i < 100; i++){
            new Thread(() -> {
                rabbitTemplate.convertAndSend(RabbitDirectConfig.DIRECT_EXCHANGE,
                        RabbitDirectConfig.DIRECT_ROUTING, "消息" + num.getAndIncrement());
            }).start();
        }
        return "success";
    }

    @RabbitListener(queues = {RabbitDirectConfig.DIRECT_QUEUE})
    @RabbitHandler
    public void processor1(String message){
        System.out.println("processor1 processing message: "+ message);
    }
    @RabbitListener(queues = {RabbitDirectConfig.DIRECT_QUEUE})
    @RabbitHandler
    public void processor2(String message){
        System.out.println("processor2 processing message: "+ message);
    }
    @RabbitListener(queues = {RabbitDirectConfig.DIRECT_QUEUE})
    @RabbitHandler
    public void processor3(String message){
        System.out.println("processor3 processing message: "+ message);
    }
}
