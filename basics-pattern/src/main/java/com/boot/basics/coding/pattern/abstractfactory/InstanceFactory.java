package com.boot.basics.coding.pattern.abstractfactory;

/**
 * @Author chencl
 * @Date 2020/7/24 14:08
 * @Version 1.0
 * @Description
 */
public class InstanceFactory extends AbstractFactory {
    @Override
    public Shape getShape(String shape) {
        Shape s = null;
        if("R".equals(shape)){
            s = new Rectangle();
        }
        if("C".equals(shape)){
            s = new Circle();
        }
        if("T".equals(shape)){
            s = new Triangle();
        }
        return s;
    }

    @Override
    public Color getColor(String color) {
        Color c = null;
        if("RED".equals(color)){
            c = new Red();
        }
        if("WHITE".equals(color)){
            c = new White();
        }
        if("BLUE".equals(color)){
            c = new Blue();
        }
        return c;
    }
}
