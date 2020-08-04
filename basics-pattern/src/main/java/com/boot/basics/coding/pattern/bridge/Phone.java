package com.boot.basics.coding.pattern.bridge;

/**
 * @Author cherrishccl
 * @Date 2020/8/4 17:46
 * @Version 1.0
 * @Description
 */
public abstract class Phone {
    public Memory memory;

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public abstract void buy();
}
