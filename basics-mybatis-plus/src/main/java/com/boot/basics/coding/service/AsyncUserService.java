package com.boot.basics.coding.service;

import com.boot.basics.coding.dao.User;
import com.boot.basics.coding.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author cherrishccl
 * @Date 2021/7/7 9:57
 * @Version 1.0
 * @Description AsyncUserService
 */
@Slf4j
@Service
public class AsyncUserService {
    @Autowired
    private UserMapper userMapper;

    @Async
    public User asyncAdd(User user){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userMapper.insert(user);
        log.info("===>ASYNC-U: {}", user);
        return user;
    }
}
