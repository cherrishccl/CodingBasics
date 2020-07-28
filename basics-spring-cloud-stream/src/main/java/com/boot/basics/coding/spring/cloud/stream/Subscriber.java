package com.boot.basics.coding.spring.cloud.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author cherrishccl
 * @Date 2020/7/28 10:05
 * @Version 1.0
 * @Description
 */
public interface Subscriber {

    @Input("ADD_CUSTOMER")
    SubscribableChannel receiver();
}
