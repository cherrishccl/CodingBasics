package com.boot.basics.coding.pattern.abstractfactory;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 14:05
 * @Version 1.0
 * @Description
 */
public class White implements Color {
    @Override
    public void fill() {
        System.out.println("白色");
    }
}
