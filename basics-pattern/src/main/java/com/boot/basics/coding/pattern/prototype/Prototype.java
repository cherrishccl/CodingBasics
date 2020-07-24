package com.boot.basics.coding.pattern.prototype;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 15:19
 * @Version 1.0
 * @Description
 */
public class Prototype implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
