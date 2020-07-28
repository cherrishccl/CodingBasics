package com.boot.basics.coding.spring.cloud.stream;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author cherrishccl
 * @Date 2020/7/28 10:09
 * @Version 1.0
 * @Description
 */
@Component
public class StreamReceiver {
    @StreamListener("ADD_CUSTOMER")
    public void receive(Message<String> message){
        System.out.println("---------------------------------");
        System.out.println(message);
        System.out.println("---------------------------------");
    }
}
