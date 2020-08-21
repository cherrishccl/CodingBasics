package com.boot.basics.coding.spring.scope;

import com.boot.basics.coding.spring.Person;
import org.springframework.context.annotation.*;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 10:22
 * @Version 1.0
 * @Description
 */
@Configuration
public class AppConfig {
    @Bean
    //@Scope("prototype")
    @Scope("thread")
    public Person personX(){
        Person person = new Person();
        person.setName("personX");
        person.setAge(123);
        System.out.println("给容器中添加PersonX....");
        return person;
    }
}
