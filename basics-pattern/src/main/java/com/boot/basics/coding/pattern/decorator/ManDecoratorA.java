package com.boot.basics.coding.pattern.decorator;

/**
 * @Author cherrishccl
 * @Date 2020/7/27 16:26
 * @Version 1.0
 * @Description
 */
public class ManDecoratorA extends Decorator{
    @Override
    public void eat() {
        super.eat();
        reEat();
        System.out.println("ManDecoratorA类");
    }
    public void reEat() {
        System.out.println("再吃一顿饭");
    }

}
