package com.boot.basics.coding.pattern.strategy;

/**
 * @Author cherrishccl
 * @Date 2020/9/3 17:03
 * @Version 1.0
 * @Description
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doMethod(){
        strategy.method();
    }
}
