package com.boot.basics.coding.pattern.abstractfactory;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 14:04
 * @Version 1.0
 * @Description
 */
public class Red implements Color{
    @Override
    public void fill() {
        System.out.println("红色");
    }
}
