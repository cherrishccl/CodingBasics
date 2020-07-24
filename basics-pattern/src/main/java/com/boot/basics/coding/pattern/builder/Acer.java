package com.boot.basics.coding.pattern.builder;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 14:53
 * @Version 1.0
 * @Description
 */
public class Acer {
    private final String cpu;
    private final String graphics;
    private final String ram;
    private final String rom;
    private final String keyboard;
    private final String mouse;
    private final String network;

    @Override
    public String toString() {
        return "Acer{" +
                "cpu='" + cpu + '\'' +
                ", graphics='" + graphics + '\'' +
                ", ram='" + ram + '\'' +
                ", rom='" + rom + '\'' +
                ", keyboard='" + keyboard + '\'' +
                ", mouse='" + mouse + '\'' +
                ", network='" + network + '\'' +
                '}';
    }

    private Acer(Builder builder) {
        this.cpu = builder.cpu;
        this.graphics = builder.graphics;
        this.ram = builder.ram;
        this.rom = builder.rom;
        this.keyboard = builder.keyboard;
        this.mouse = builder.mouse;
        this.network = builder.network;
    }

    public static class Builder{
        private String cpu;
        private String graphics;
        private String ram;
        private String rom;
        private String keyboard;
        private String mouse;
        private String network;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setGraphics(String graphics) {
            this.graphics = graphics;
            return this;
        }

        public Builder setRam(String ram) {
            this.ram = ram;
            return this;
        }

        public Builder setRom(String rom) {
            this.rom = rom;
            return this;
        }

        public Builder setKeyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Builder setMouse(String mouse) {
            this.mouse = mouse;
            return this;
        }

        public Builder setNetwork(String network) {
            this.network = network;
            return this;
        }

        public Acer build(){
            return new Acer(this);
        }
    }
}
