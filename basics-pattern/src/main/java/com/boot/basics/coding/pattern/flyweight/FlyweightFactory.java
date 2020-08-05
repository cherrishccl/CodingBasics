package com.boot.basics.coding.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author cherrishccl
 * @Date 2020/8/5 17:32
 * @Version 1.0
 * @Description
 */
public class FlyweightFactory {
    private static Map flyweights = new HashMap();

    public FlyweightFactory(String arg) {
        flyweights.put(arg, new FlyweightImpl());
    }

    public static Flyweight getFlyweight(String key) {
        if (flyweights.get(key) == null) {
            flyweights.put(key, new FlyweightImpl());
        }
        return (Flyweight) flyweights.get(key);
    }

    public static int getSize() {
        return flyweights.size();
    }
}
