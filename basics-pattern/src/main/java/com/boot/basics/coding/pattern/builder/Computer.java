package com.boot.basics.coding.pattern.builder;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 14:29
 * @Version 1.0
 * @Description
 */
public class Computer {
    private String cpu;
    private String graphics;
    private String ram;
    private String rom;
    private String keyboard;
    private String mouse;
    private String network;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", graphics='" + graphics + '\'' +
                ", ram='" + ram + '\'' +
                ", rom='" + rom + '\'' +
                ", keyboard='" + keyboard + '\'' +
                ", mouse='" + mouse + '\'' +
                ", network='" + network + '\'' +
                '}';
    }
}
