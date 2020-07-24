package com.boot.basics.coding.pattern.builder;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 14:44
 * @Version 1.0
 * @Description
 */
public class Worker {
    public ComputerBuilder getComputerBuilder() {
        return computerBuilder;
    }

    private ComputerBuilder computerBuilder;

    public void setComputerBuilder(ComputerBuilder computerBuilder) {
        this.computerBuilder = computerBuilder;
    }

    public void build(){
        computerBuilder.setCpu();
        computerBuilder.setGraphics();
        computerBuilder.setNetwork();
        computerBuilder.setRam();
        computerBuilder.setRom();
        computerBuilder.setKeyboard();
        computerBuilder.setMouse();
    }

    public Computer getComputer(){
        return computerBuilder.getComputer();
    }
}
