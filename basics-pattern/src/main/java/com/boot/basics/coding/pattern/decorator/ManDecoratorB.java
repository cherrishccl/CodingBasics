package com.boot.basics.coding.pattern.decorator;

/**
 * @Author cherrishccl
 * @Date 2020/7/27 16:26
 * @Version 1.0
 * @Description
 */
public class ManDecoratorB extends Decorator{
    @Override
    public void eat() {
        super.eat();
        System.out.println("===============");
        System.out.println("ManDecoratorB类");

    }
}
