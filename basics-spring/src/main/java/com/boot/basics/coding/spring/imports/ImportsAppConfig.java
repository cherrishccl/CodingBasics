package com.boot.basics.coding.spring.imports;

import com.boot.basics.coding.spring.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 10:22
 * @Version 1.0
 * @Description
 */
@Configuration
@Import({Department.class, Employee.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class ImportsAppConfig {
    @Bean
    public Person personD(){
        Person person = new Person();
        person.setName("personD");
        person.setAge(123);
        System.out.println("给容器中添加PersonX....");
        return person;
    }
}
