package com.boot.basics.coding.pattern.command;

/**
 * @Author cherrishccl
 * @Date 2020/8/7 9:26
 * @Version 1.0
 * @Description
 */
public class CommandImpl extends Command{
    public CommandImpl(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        receiver.receive();
    }
}
