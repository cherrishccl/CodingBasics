package com.boot.basics.coding.spring.condition;

import com.boot.basics.coding.spring.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 10:22
 * @Version 1.0
 * @Description
 * @Conditional注解也可以标注在类上，标注在类上含义为：满足当前条件，这个类中配置的所有bean注册才能生效，大家可以自行验证@Conditional注解标注在类上的情况
 */
@Configuration
public class AppConfig {
    @Conditional({WindowsCondition.class})
    @Bean
    public Person personX(){
        Person person = new Person();
        person.setName("personX");
        person.setAge(123);
        System.out.println("给容器中添加PersonX....");
        return person;
    }
    @Conditional({LinuxCondition.class})
    @Bean
    public Person personY(){
        Person person = new Person();
        person.setName("personY");
        person.setAge(124);
        System.out.println("给容器中添加PersonY....");
        return person;
    }
    @Bean
    public Person personZ(){
        Person person = new Person();
        person.setName("personZ");
        person.setAge(125);
        System.out.println("给容器中添加PersonZ....");
        return person;
    }
}
