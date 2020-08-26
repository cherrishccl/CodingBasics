package com.boot.basics.coding.spring.context;

import com.boot.basics.coding.spring.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 10:22
 * @Version 1.0
 * @Description
 */
@Configuration
public class ContextAppConfig {
    @Bean("person1")
    public Person person(){
        Person person = new Person();
        person.setName("ccccc");
        person.setAge(123);
        return person;
    }
}
