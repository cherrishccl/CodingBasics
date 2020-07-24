package com.boot.basics.coding.pattern.sigleton;

/**
 * @Author cherrishccl
 * @Date 2020/7/23 15:56
 * @Version 1.0
 * @Description 懒汉模式（双重锁同步锁单例模式）单例实例在第一次使用的时候进行创建，这个类是线程安全的
 */
public class SigletonExample5 {
    private static volatile SigletonExample5 instance = null;
    /**
     * 懒汉模式（双重锁同步锁单例模式）单例实例在第一次使用的时候进行创建，这个类是线程安全的，使
     * 用的是 volatile + 双重检测机制来禁止指令重排达到线程安全
     */
    public static SigletonExample5 getInstance() {
        if(null == instance){
            synchronized (SigletonExample5.class){
                if(null == instance){
                    instance = new SigletonExample5();
                }
            }
        }
        return instance;
    }

    private SigletonExample5(){}
}
