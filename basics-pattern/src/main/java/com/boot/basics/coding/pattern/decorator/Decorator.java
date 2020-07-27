package com.boot.basics.coding.pattern.decorator;

/**
 * @Author cherrishccl
 * @Date 2020/7/27 16:24
 * @Version 1.0
 * @Description
 */
public abstract class Decorator implements Person{
    public void setPerson(Person persion) {
        this.person = persion;
    }

    protected Person person;

    @Override
    public void eat() {
        person.eat();
    }
}
