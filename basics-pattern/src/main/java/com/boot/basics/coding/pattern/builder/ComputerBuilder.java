package com.boot.basics.coding.pattern.builder;

/**
 * @Author cherrishcl
 * @Date 2020/7/24 14:31
 * @Version 1.0
 * @Description
 */
public interface ComputerBuilder {
    void setCpu();

    void setGraphics();

    void setRam();

    void setRom();

    void setKeyboard();
    void setMouse();

    void setNetwork();

    Computer getComputer();
}
