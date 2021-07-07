package com.boot.basics.coding.reflect;

/**
 * @Author cherrishccl
 * @Date 2021/7/7 10:41
 * @Version 1.0
 * @Description Apple
 */
public class Apple {
    private String color;
    private Double price;
    private String origin;

    //public Apple() {}

    public Apple(String color, Double price, String origin) {
        this.color = color;
        this.price = price;
        this.origin = origin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
