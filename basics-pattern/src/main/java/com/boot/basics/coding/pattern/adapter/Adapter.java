package com.boot.basics.coding.pattern.adapter;

/**
 * @Author cherrishccl
 * @Date 2020/7/27 16:16
 * @Version 1.0
 * @Description
 */
public class Adapter implements Target {
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    private Adaptee adaptee;
    @Override
    public void adapteeMethod() {
        adaptee.adapteeMethod();
    }

    @Override
    public void adapterMethod() {
        System.out.println("Adapter method");
    }
}
