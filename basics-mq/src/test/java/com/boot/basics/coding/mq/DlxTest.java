package com.boot.basics.coding.mq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author chencl
 * @Date 2020/10/29 18:45
 * @Version 1.0
 * @Description
 */
@SpringBootTest
public class DlxTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test(){
        rabbitTemplate.convertAndSend("user-exchange", "user.abc", "111111111111111111");
    }
}
