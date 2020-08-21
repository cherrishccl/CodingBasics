package com.boot.basics.coding.spring.autowireds;

/**
 * @Author cherrishccl
 * @Date 2020/8/21 14:05
 * @Version 1.0
 * @Description
 */
public class Fish {
    private Cat cat;
    public void setCat(Cat cat) {
        this.cat = cat;
    }
    @Override
    public String toString() {
        return "Fish{" + "cat=" + cat + '}';
    }
}
