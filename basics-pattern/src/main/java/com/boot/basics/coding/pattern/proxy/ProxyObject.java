package com.boot.basics.coding.pattern.proxy;

/**
 * @Author cherrishccl
 * @Date 2020/8/3 15:44
 * @Version 1.0
 * @Description
 */
public class ProxyObject implements Object {
    public ProxyObject(){
        System.out.println("这是代理类");
        obj = new ObjectImpl();
    }
    Object obj;
    @Override
    public void action() {
        System.out.println("代理开始");
        obj.action();
        System.out.println("代理结束");
    }
}
