package com.boot.basics.coding.proxy1;

/**
 * @Author cherrishccl
 * @Date 2021/7/14 14:58
 * @Version 1.0
 * @Description UserDaoProxy
 */
public class UserDaoProxy implements IUserDao{
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println(this.getClass().getSimpleName() + "===>BEGIN");
        target.save();
        System.out.println(this.getClass().getSimpleName() + "===>END");
    }
}
