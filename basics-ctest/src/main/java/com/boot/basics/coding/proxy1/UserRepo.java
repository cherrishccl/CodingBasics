package com.boot.basics.coding.proxy1;

/**
 * @Author cherrishccl
 * @Date 2021/7/14 15:16
 * @Version 1.0
 * @Description UserRepo
 */
public class UserRepo {
    public void save() {
        System.out.println(this.getClass().getSimpleName() + " : " + this.getClass().getName());
    }
}
