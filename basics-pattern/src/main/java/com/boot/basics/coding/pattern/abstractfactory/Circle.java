package com.boot.basics.coding.pattern.abstractfactory;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 14:01
 * @Version 1.0
 * @Description
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("画圆");
    }
}
