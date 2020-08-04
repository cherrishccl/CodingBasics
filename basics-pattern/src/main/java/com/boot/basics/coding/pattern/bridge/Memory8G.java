package com.boot.basics.coding.pattern.bridge;

/**
 * @Author cherrishccl
 * @Date 2020/8/4 17:28
 * @Version 1.0
 * @Description
 */
public class Memory8G implements Memory{
    @Override
    public void addMemory() {
        System.out.println("8G内存");
    }
}
