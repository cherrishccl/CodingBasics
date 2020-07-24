package com.boot.basics.coding.pattern.builder;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 14:36
 * @Version 1.0
 * @Description
 */
public class LenovoBuilder implements ComputerBuilder {
    private Computer computer;

    public LenovoBuilder() {
        this.computer = new Computer();
    }

    @Override
    public void setCpu() {
        computer.setCpu("Intel");
    }

    @Override
    public void setGraphics() {
        computer.setGraphics("NVIDIA");
    }

    @Override
    public void setRam() {
        computer.setRam("Kingston");
    }

    @Override
    public void setRom() {
        computer.setRom("Samsung");
    }

    @Override
    public void setKeyboard() {
        computer.setKeyboard("chery");
    }

    @Override
    public void setMouse() {
        computer.setMouse("Logitech");
    }

    @Override
    public void setNetwork() {
        computer.setNetwork("WAN");
    }

    @Override
    public Computer getComputer() {
        return this.computer;
    }
}
