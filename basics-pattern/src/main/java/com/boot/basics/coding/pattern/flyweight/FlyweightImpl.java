package com.boot.basics.coding.pattern.flyweight;

/**
 * @Author cherrishccl
 * @Date 2020/8/5 17:31
 * @Version 1.0
 * @Description
 */
public class FlyweightImpl implements Flyweight {
    @Override
    public void action(int arg) {
        System.out.println("参数值: " + arg);
    }
}
