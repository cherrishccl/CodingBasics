package com.boot.basics.coding.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author cherrishccl
 * @Date 2020/8/14 14:16
 * @Version 1.0
 * @Description
 */
public class HelloInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("start time -----> "+ System.currentTimeMillis());
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("end time -----> "+ System.currentTimeMillis());
        return result;
    }
}
