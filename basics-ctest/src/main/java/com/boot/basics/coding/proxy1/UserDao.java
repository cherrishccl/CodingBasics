package com.boot.basics.coding.proxy1;

/**
 * @Author cherrishccl
 * @Date 2021/7/14 14:57
 * @Version 1.0
 * @Description UserDao
 */
public class UserDao implements IUserDao{
    @Override
    public void save() {
        System.out.println(this.getClass().getSimpleName() + " : " + this.getClass().getName());
    }
}
