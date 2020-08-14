package com.boot.basics.coding.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author cherrishccl
 * @Date 2020/8/14 13:57
 * @Version 1.0
 * @Description
 */
public class ProxyHandler implements InvocationHandler {

    private Object object;

    public ProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoke " + method.getName());
        Object result = method.invoke(object, args);
        System.out.println("After invoke " + method.getName());
        return result;
    }
}
