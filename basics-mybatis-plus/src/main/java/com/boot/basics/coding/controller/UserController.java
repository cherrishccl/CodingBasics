package com.boot.basics.coding.controller;

import com.boot.basics.coding.dao.User;
import com.boot.basics.coding.dao.UserMapper;
import com.boot.basics.coding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author chencl
 * @Date 2021/7/6 14:57
 * @Version 1.0
 * @Description UserController
 */
@RequestMapping(value = "/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    @PostMapping("/add")
    public User add(@RequestBody User user){
        return userService.add(user);
    }
}
