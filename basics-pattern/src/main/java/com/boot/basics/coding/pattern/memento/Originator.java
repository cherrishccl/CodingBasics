package com.boot.basics.coding.pattern.memento;

/**
 * @Author cherrishccl
 * @Date 2020/8/25 16:02
 * @Version 1.0
 * @Description
 */
public class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento createMemento() {
        return new Memento(state);
    }

    public void setMemento(Memento memento) {
        state = memento.getState();
    }

    public void showState() {
        System.out.println(state);
    }
}
