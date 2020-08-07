package com.boot.basics.coding.pattern.command;

/**
 * @Author cherrishccl
 * @Date 2020/8/7 9:23
 * @Version 1.0
 * @Description
 */
public abstract class Command {
    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    protected Receiver receiver;
    public abstract void execute();
}
