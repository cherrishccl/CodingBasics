package com.boot.basics.coding.pattern.iterator;

/**
 * @Author cherrishccl
 * @Date 2020/8/19 10:04
 * @Version 1.0
 * @Description
 */
public interface List {
    Iterator iterator();
    Object get(int index);
    int getSize();
    void add(Object obj);
}
