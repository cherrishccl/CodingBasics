package com.boot.basics.coding.pattern.abstractfactory;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 14:03
 * @Version 1.0
 * @Description
 */
public class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("画三角");
    }
}
