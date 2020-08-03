package com.boot.basics.coding.pattern.facade;

/**
 * @Author cherrishccl
 * @Date 2020/8/3 15:35
 * @Version 1.0
 * @Description
 */
public class Facade {
    ServiceA sa;
    ServiceB sb;
    ServiceC sc;

    public Facade() {
        sa = new ServiceAImpl();
        sb = new ServiceBImpl();
        sc = new ServiceCImpl();
    }

    public void methodA(){
        sa.methodA();
        sb.methodB();
    }

    public void methodB(){
        sb.methodB();
        sc.methodC();
    }

    public void methodC(){
        sc.methodC();
        sa.methodA();
    }
}
