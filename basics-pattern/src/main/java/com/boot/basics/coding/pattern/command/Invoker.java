package com.boot.basics.coding.pattern.command;

/**
 * @Author cherrishccl
 * @Date 2020/8/7 9:29
 * @Version 1.0
 * @Description
 */
public class Invoker {
    private Command command;
    public void setCommand(Command command){
        this.command = command;
    }
    public void execute(){
        command.execute();
    }
}
