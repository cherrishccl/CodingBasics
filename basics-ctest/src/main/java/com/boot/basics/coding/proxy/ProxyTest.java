package com.boot.basics.coding.proxy;

import com.boot.basics.coding.proxy.cglib.HelloInterceptor;
import com.boot.basics.coding.proxy.cglib.HelloService;
import com.boot.basics.coding.proxy.jdk.ProxyHandler;
import com.boot.basics.coding.proxy.jdk.ShoppingService;
import com.boot.basics.coding.proxy.jdk.ShoppingServiceImpl;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @Author cherrishccl
 * @Date 2020/8/14 14:03
 * @Version 1.0
 * @Description
 */
public class ProxyTest {
    public static void main(String[] args) {
        ShoppingService shoppingService = new ShoppingServiceImpl();
        ProxyHandler proxyHandler = new ProxyHandler(shoppingService);
        ShoppingService proxyShoppingService = (ShoppingService) Proxy.newProxyInstance(proxyHandler.getClass().getClassLoader(),
                shoppingService.getClass().getInterfaces(), proxyHandler);
        proxyShoppingService.buy();


        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloService.class);
        enhancer.setCallback(new HelloInterceptor());
        HelloService helloService = (HelloService) enhancer.create();
        helloService.say();
    }
}
