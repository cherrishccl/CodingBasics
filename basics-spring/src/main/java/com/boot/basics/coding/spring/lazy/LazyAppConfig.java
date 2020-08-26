package com.boot.basics.coding.spring.lazy;

import com.boot.basics.coding.spring.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 10:22
 * @Version 1.0
 * @Description
 */
@Configuration
public class LazyAppConfig {
    @Bean
    @Lazy
    public Person personE(){
        Person person = new Person();
        person.setName("personE");
        person.setAge(123);
        System.out.println("给容器中添加PersonX....");
        return person;
    }
}
