package com.boot.basics.coding.spring.factory;

import com.boot.basics.coding.spring.Person;
import org.springframework.beans.factory.FactoryBean;

import java.util.Calendar;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 14:44
 * @Version 1.0
 * @Description
 */
public class PersonFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        Person person = new Person();
        person.setName("personX");
        person.setAge(123);
        System.out.println("给容器中添加PersonX....");
        return person;
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
