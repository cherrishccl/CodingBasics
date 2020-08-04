package com.boot.basics.coding.pattern.bridge;

/**
 * @Author cherrishccl
 * @Date 2020/8/4 17:55
 * @Version 1.0
 * @Description
 */
public class Xiaomi extends Phone {
    @Override
    public void buy() {
        memory.addMemory();
        System.out.println("buy xiaomi");
    }
}
