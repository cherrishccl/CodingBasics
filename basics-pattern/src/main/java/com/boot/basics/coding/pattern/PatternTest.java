package com.boot.basics.coding.pattern;

import com.boot.basics.coding.pattern.abstractfactory.AbstractFactory;
import com.boot.basics.coding.pattern.abstractfactory.InstanceFactory;
import com.boot.basics.coding.pattern.factory.ManagerFactory;
import com.boot.basics.coding.pattern.factory.ProgramerFactory;
import com.boot.basics.coding.pattern.factory.WorkFactory;
import com.boot.basics.coding.pattern.sigleton.*;

/**
 * @Author cherrishccl
 * @Date 2020/7/23 16:17
 * @Version 1.0
 * @Description
 */
public class PatternTest {
    public static void main(String[] args) {
        SigletonExample1 sigletonExample1 = SigletonExample1.getInstance();
        SigletonExample2 sigletonExample2 = SigletonExample2.getInstance();
        SigletonExample3 sigletonExample3 = SigletonExample3.getInstance();
        SigletonExample4 sigletonExample4 = SigletonExample4.getInstance();
        SigletonExample5 sigletonExample5 = SigletonExample5.getInstance();
        SigletonExample6 sigletonExample6 = SigletonExample6.getInstance();
        SigletonExample7 sigletonExample7 = SigletonExample7.getInstance();

        WorkFactory managerFactory = new ManagerFactory();
        managerFactory.getWork().doWork();
        WorkFactory programerFactory = new ProgramerFactory();
        programerFactory.getWork().doWork();

        AbstractFactory factory = new InstanceFactory();
        factory.getShape("R").draw();
        factory.getColor("RED").fill();
    }
}
