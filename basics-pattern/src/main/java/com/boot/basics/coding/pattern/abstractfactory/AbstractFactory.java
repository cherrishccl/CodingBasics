package com.boot.basics.coding.pattern.abstractfactory;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 14:06
 * @Version 1.0
 * @Description
 */
public abstract class AbstractFactory {
    public abstract Shape getShape(String shape);
    public abstract Color getColor(String color);
}
