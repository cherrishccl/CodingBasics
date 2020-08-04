package com.boot.basics.coding.pattern.bridge;

/**
 * @Author cherrishccl
 * @Date 2020/8/4 17:28
 * @Version 1.0
 * @Description
 */
public class Memory6G implements Memory{
    @Override
    public void addMemory() {
        System.out.println("6G内存");
    }
}
