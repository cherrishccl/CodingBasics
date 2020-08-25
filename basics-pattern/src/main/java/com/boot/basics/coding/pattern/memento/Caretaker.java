package com.boot.basics.coding.pattern.memento;

/**
 * @Author cherrishccl
 * @Date 2020/8/25 16:01
 * @Version 1.0
 * @Description
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return this.memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
