package com.boot.basics.coding.service;

import com.boot.basics.coding.dao.User;
import com.boot.basics.coding.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2021/7/7 9:37
 * @Version 1.0
 * @Description UserService
 */
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AsyncUserService asyncUserService;

    public List<User> list(){
        return userMapper.selectList(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public User add(User user){
        userMapper.insert(user);

        User u = new User();
        u.setUserName(System.currentTimeMillis() + "");
        u.setAge(12);
        asyncUserService.asyncAdd(u);
        log.info("AsyncU={}", u);
        if(1 != 1){
            throw new RuntimeException("xxxxxxxxxxxxxxxxxxx");
        }
        log.info("USER={}", user);
        return user;
    }
}
