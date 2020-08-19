package com.boot.basics.coding.pattern.iterator;

/**
 * @Author cherrishccl
 * @Date 2020/8/19 9:58
 * @Version 1.0
 * @Description
 */
public interface Iterator {
    Object next();
    void first();
    void last();
    boolean hasNext();
}
