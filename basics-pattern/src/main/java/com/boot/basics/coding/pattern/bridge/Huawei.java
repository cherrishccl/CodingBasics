package com.boot.basics.coding.pattern.bridge;

/**
 * @Author cherrishccl
 * @Date 2020/8/4 17:53
 * @Version 1.0
 * @Description
 */
public class Huawei extends Phone {
    @Override
    public void buy() {
        memory.addMemory();
        System.out.println("buy huawei");
    }
}
