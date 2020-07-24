package com.boot.basics.coding.pattern.sigleton;

/**
 * @Author cherrishccl
 * @Date 2020/7/23 15:56
 * @Version 1.0
 * @Description 懒汉模式，单例实例在第一次使用的时候进行创建，这个类是线程安全的，但是这个写法不推荐
 */
public class SigletonExample3 {
    private static SigletonExample3 instance = null;

    public static synchronized SigletonExample3 getInstance() {
        if(null == instance){
            instance = new SigletonExample3();
        }
        return instance;
    }

    private SigletonExample3(){}
}
