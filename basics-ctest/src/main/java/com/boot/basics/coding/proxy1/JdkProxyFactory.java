package com.boot.basics.coding.proxy1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author cherrishccl
 * @Date 2021/7/14 15:08
 * @Version 1.0
 * @Description ProxyFactory
 */
public class JdkProxyFactory {
    private Object target;

    public JdkProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("JdkProxyFactory===>BEGIN");
                Object result = method.invoke(target, args);
                System.out.println("JdkProxyFactory===>END");
                return result;
            }
        });
    }
}
