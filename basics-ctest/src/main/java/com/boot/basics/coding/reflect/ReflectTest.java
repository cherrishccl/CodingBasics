package com.boot.basics.coding.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author cherrishccl
 * @Date 2021/7/7 10:43
 * @Version 1.0
 * @Description
 */
public class ReflectTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Class.forName("com.boot.basics.coding.reflect.Apple");
        Constructor constructor = clazz.getConstructor(String.class, Double.class, String.class);
        Method setColorMethod = clazz.getMethod("setColor", String.class);
        Method getColorMethod = clazz.getMethod("getColor");
        Object instance = constructor.newInstance("red", 0.0, "CQ");
        Object result = new Object();
        result = getColorMethod.invoke(instance);
        System.out.println(result.toString());
        //Object instance = clazz.newInstance();// 需要无参构造
        setColorMethod.invoke(instance, "blue");
        result = getColorMethod.invoke(instance);
        System.out.println(result.toString());
    }
}
