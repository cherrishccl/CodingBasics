package com.boot.basics.coding.proxy1;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author cherrishccl
 * @Date 2021/7/14 15:17
 * @Version 1.0
 * @Description CglibProxyFactory
 */
public class CglibProxyFactory implements MethodInterceptor {
    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("CglibProxyFactory===>BEGIN");
        Object result = method.invoke(target, args);
        System.out.println("CglibProxyFactory===>END");
        return result;
    }
}
