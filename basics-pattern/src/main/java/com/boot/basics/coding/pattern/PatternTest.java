package com.boot.basics.coding.pattern;

import com.boot.basics.coding.pattern.abstractfactory.AbstractFactory;
import com.boot.basics.coding.pattern.abstractfactory.InstanceFactory;
import com.boot.basics.coding.pattern.adapter.Adaptee;
import com.boot.basics.coding.pattern.adapter.Adapter;
import com.boot.basics.coding.pattern.adapter.Target;
import com.boot.basics.coding.pattern.bridge.*;
import com.boot.basics.coding.pattern.builder.Acer;
import com.boot.basics.coding.pattern.builder.Computer;
import com.boot.basics.coding.pattern.builder.LenovoBuilder;
import com.boot.basics.coding.pattern.builder.Worker;
import com.boot.basics.coding.pattern.chainofresponsibility.*;
import com.boot.basics.coding.pattern.command.Command;
import com.boot.basics.coding.pattern.command.CommandImpl;
import com.boot.basics.coding.pattern.command.Invoker;
import com.boot.basics.coding.pattern.command.Receiver;
import com.boot.basics.coding.pattern.composite.ConcreteCompany;
import com.boot.basics.coding.pattern.composite.Department1;
import com.boot.basics.coding.pattern.composite.Department2;
import com.boot.basics.coding.pattern.decorator.Man;
import com.boot.basics.coding.pattern.decorator.ManDecoratorA;
import com.boot.basics.coding.pattern.decorator.ManDecoratorB;
import com.boot.basics.coding.pattern.decorator.Person;
import com.boot.basics.coding.pattern.facade.*;
import com.boot.basics.coding.pattern.factory.ManagerFactory;
import com.boot.basics.coding.pattern.factory.ProgramerFactory;
import com.boot.basics.coding.pattern.factory.WorkFactory;
import com.boot.basics.coding.pattern.flyweight.Flyweight;
import com.boot.basics.coding.pattern.flyweight.FlyweightFactory;
import com.boot.basics.coding.pattern.interpreter.AdvanceExpression;
import com.boot.basics.coding.pattern.interpreter.Context;
import com.boot.basics.coding.pattern.interpreter.Expression;
import com.boot.basics.coding.pattern.interpreter.SimpleExpression;
import com.boot.basics.coding.pattern.prototype.Prototype;
import com.boot.basics.coding.pattern.prototype.PrototypeConcrete;
import com.boot.basics.coding.pattern.proxy.Object;
import com.boot.basics.coding.pattern.proxy.ProxyObject;
import com.boot.basics.coding.pattern.sigleton.*;

/**
 * @Author cherrishccl
 * @Date 2020/7/23 16:17
 * @Version 1.0
 * @Description
 */
public class PatternTest {
    public static void main(String[] args) throws CloneNotSupportedException {
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

        Worker worker = new Worker();
        worker.setComputerBuilder(new LenovoBuilder());
        worker.build();
        Computer computer = worker.getComputer();
        System.out.println(computer.toString());

        Acer acer = new Acer.Builder().setCpu("AMD").setGraphics("NV").setNetwork("WL")
                .setKeyboard("CH").setMouse("LG").setRam("KI").setRom("SM").build();
        System.out.println(acer);

        Prototype pro1 = new PrototypeConcrete("原型");
        Prototype pro2 = (Prototype) pro1.clone();
        System.out.println(pro1.getName());
        System.out.println(pro2.getName());

        Target target = new Adapter(new Adaptee());
        target.adapteeMethod();
        target.adapterMethod();

        Man man = new Man();
        ManDecoratorA ma = new ManDecoratorA();
        ma.setPerson(man);

        ManDecoratorB mb = new ManDecoratorB();
        mb.setPerson(man);
        ma.eat();
        mb.eat();

        ServiceA sa = new ServiceAImpl();
        ServiceB sb = new ServiceBImpl();
        sa.methodA();
        sb.methodB();
        System.out.println("==========");
        Facade facade = new Facade();
        facade.methodA();
        facade.methodB();

        Object object = new ProxyObject();
        object.action();

        Phone huawei = new Huawei();
        huawei.setMemory(new Memory6G());
        huawei.buy();
        Phone xiaomi = new Xiaomi();
        xiaomi.setMemory(new Memory8G());
        xiaomi.buy();


        //根节点
        ConcreteCompany root = new ConcreteCompany("总公司");
        root.add(new Department1("总公司部门1"));
        root.add(new Department2("总公司部门2"));
        //子节点
        ConcreteCompany comp = new ConcreteCompany("分公司");
        comp.add(new Department1("分公司部门1"));
        comp.add(new Department2("分公司部门2"));
        root.add(comp);
        //叶子节点1
        ConcreteCompany comp1 = new ConcreteCompany("办事处1");
        comp1.add(new Department1("办事处1部门1"));
        comp1.add(new Department2("办事处1部门2"));
        comp.add(comp1);
        //叶子节点2
        ConcreteCompany comp2 = new ConcreteCompany("办事处2");
        comp2.add(new Department1("办事处2部门1"));
        comp2.add(new Department2("办事处2部门2"));
        comp.add(comp2);
        root.display(1);
        root.duty();

        Flyweight fly1 = FlyweightFactory.getFlyweight("a");
        fly1.action(1);
        Flyweight fly2 = FlyweightFactory.getFlyweight("a");
        System.out.println(fly1 == fly2);
        Flyweight fly3 = FlyweightFactory.getFlyweight("b");
        fly3.action(2);
        Flyweight fly4 = FlyweightFactory.getFlyweight("c");
        fly4.action(3);
        Flyweight fly5 = FlyweightFactory.getFlyweight("d");
        fly4.action(4);
        System.out.println(FlyweightFactory.getSize());
        System.out.println("------------------------------------");

        RequestHandle hr = new HRRequestHandle();
        RequestHandle pm = new PMRequestHandle(hr);
        RequestHandle tl = new TLRequestHandle(pm);
        //team leader处理离职请求
        Request request = new Request();
        tl.handle(request); System.out.println("===========");
        //team leader处理加薪请求
        request = new SalaryRequest();
        tl.handle(request);
        System.out.println("========");
        //项目经理上理辞职请求
        request = new DimissionRequest();
        pm.handle(request);
        /*****************************/
        Receiver rc = new Receiver();
        Command command = new CommandImpl(rc);
        Invoker ir = new Invoker();
        ir.setCommand(command);
        ir.execute();

        /***********************/
        Context ctx = new Context();
        ctx.add(new SimpleExpression());
        ctx.add(new AdvanceExpression());
        ctx.add(new SimpleExpression());
        for (Expression eps : ctx.getList()) {
            eps.interpret(ctx);
        }
    }
}
