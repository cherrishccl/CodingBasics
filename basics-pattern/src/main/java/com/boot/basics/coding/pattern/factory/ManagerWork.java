package com.boot.basics.coding.pattern.factory;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 13:46
 * @Version 1.0
 * @Description
 */
public class ManagerWork implements Work {
    @Override
    public void doWork() {
        System.out.println("经理布置任务");
    }
}
