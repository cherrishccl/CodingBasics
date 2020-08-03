package com.boot.basics.coding.pattern.proxy;

/**
 * @Author cherrishccl
 * @Date 2020/8/3 15:45
 * @Version 1.0
 * @Description
 */
public class ObjectImpl implements Object {
    @Override
    public void action() {
        System.out.println("========");
        System.out.println("========");
        System.out.println("这是被代理的类");
        System.out.println("========");
        System.out.println("========");
    }
}
