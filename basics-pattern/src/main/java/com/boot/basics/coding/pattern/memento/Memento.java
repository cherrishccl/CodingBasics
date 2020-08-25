package com.boot.basics.coding.pattern.memento;

/**
 * @Author cherrishccl
 * @Date 2020/8/25 16:01
 * @Version 1.0
 * @Description
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
