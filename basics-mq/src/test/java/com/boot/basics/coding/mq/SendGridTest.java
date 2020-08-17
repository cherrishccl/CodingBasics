package com.boot.basics.coding.mq;

import com.boot.basics.coding.mq.rabbit.SendGridUtils;

import java.io.IOException;

/**
 * @Author cherrishccl
 * @Date 2020/8/14 15:23
 * @Version 1.0
 * @Description
 */
public class SendGridTest {
    public static void main(String[] args) throws IOException {
        SendGridUtils sendGridUtils = new SendGridUtils();
        sendGridUtils.sendMail("1244537369@qq.com", "fanfan", "fanfanfanfan");
    }
}
