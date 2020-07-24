package com.boot.basics.coding.pattern.factory;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 13:47
 * @Version 1.0
 * @Description
 */
public class ManagerFactory implements WorkFactory {
    @Override
    public Work getWork() {
        return new ManagerWork();
    }
}
