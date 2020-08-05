package com.boot.basics.coding.pattern.composite;

/**
 * @Author cherrishccl
 * @Date 2020/8/5 17:14
 * @Version 1.0
 * @Description
 */
public interface Company {
    void add(Company company);
    void remove(Company company);
    void display(int depth);
    void duty();
}
