package com.boot.basics.coding.mq.rabbit.controller;

import com.boot.basics.coding.mq.rabbit.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cherrishccl
 * @Date 2020/8/6 10:12
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping(value = "/rabbit")
public class RabbitController {
    @Autowired
    private RabbitService rabbitService;
    @GetMapping(value = "/req")
    public Map<String, Object> req(){
        rabbitService.sendReq();
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", "200");
        result.put("message", "success");
        result.put("resTime", System.currentTimeMillis());
        return result;
    }
}
