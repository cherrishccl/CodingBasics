package com.boot.basics.coding.pattern;

/**
 * @Author cherrishccl
 * @Date 2020/7/23 15:56
 * @Version 1.0
 * @Description 饿汉模式，单例实例在类装载的时候进行创建，是线程安全的
 */
public class SigletonExample2 {
    private static SigletonExample2 instance = new SigletonExample2();

    public static SigletonExample2 getInstance() {
        return instance;
    }

    private SigletonExample2(){}
}
