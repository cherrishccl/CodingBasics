package com.cherrishccl.coding.basics.pattern;

/**
 * @Author cherrishccl
 * @Date 2020/7/23 15:56
 * @Version 1.0
 * @Description 懒汉模式，单例实例在第一次使用的时候进行创建，这个类是线程不安全的
 */
public class SigletonExample1 {
    private static SigletonExample1 instance = null;

    public static SigletonExample1 getInstance() {
        // 多线程同时调用，可能会创建多个对象
        if(null == instance){
            instance = new SigletonExample1();
        }
        return instance;
    }

    private SigletonExample1(){}
}
