package com.boot.basics.coding.mq.rabbit.service;

import com.boot.basics.coding.mq.rabbit.*;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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

    public RabbitService() {
    }

    public String sendReq(String type){
        if(RabbitConfig.EX_DIRECT.equals(type)){
            direct();
        }else if(RabbitConfig.EX_TOPIC.equals(type)){
            topic();
        }else if(RabbitConfig.EX_FANOUT.equals(type)){
            fanout();
        }else if(RabbitConfig.EX_HEADERS.equals(type)){

        }else {

        }
        return "success";
    }

    private void fanout(){
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUT_EXCHANGE, null, "fanout message000000");
    }
    private void topic(){
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_EXCHANGE, RabbitTopicConfig.TOPIC_QUEUE1, "topic.queue111111");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPIC_EXCHANGE, RabbitTopicConfig.TOPIC_QUEUE2, "topic.queue222222");
    }

    private void direct(){
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

    public void sendMail(){
        String msgId = UUID.randomUUID().toString();
        // TODO mailId 记录消息, 入库
        CorrelationData correlationData = new CorrelationData(msgId);
        // 发送消息
        rabbitTemplate.convertAndSend(MailQueueConfig.MAIL_EXCHANGE_NAME,

                MailQueueConfig.MAIL_ROUTING_KEY_NAME, "发送邮件", correlationData);

    }

    @RabbitListener(queues = {MailQueueConfig.MAIL_QUEUE_NAME})
    @RabbitHandler
    public void receiveMail(Message message, Channel channel) throws IOException {
        System.out.println("邮件消息处理: "+ message);
        // 从邮件中获取 msgId
        // 判断邮件是否已消费, 重复则return

        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();
        // 发送邮件是否成功
        boolean success = false;
        if (success) {
            // msgId更新邮件消费状态
            // 消费确认
            channel.basicAck(tag, false);
        } else {
            // 失败则重新投递
            channel.basicNack(tag, false, true);
        }
    }

}
