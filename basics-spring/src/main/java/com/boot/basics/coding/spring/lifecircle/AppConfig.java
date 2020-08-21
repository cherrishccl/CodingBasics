package com.boot.basics.coding.spring.lifecircle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author cherrishccl
 * @Date 2020/8/21 9:41
 * @Version 1.0
 * @Description
 * 将bean设置成多实例时，Spring不会自动调用bean对象的销毁方法。
 * 至于多实例bean对象何时销毁，那就是程序员自己的事情了！！Spring容器不再管理多实例bean。
 */
@Configuration
@ComponentScan(basePackages = {"com.boot.basics.coding.spring.lifecircle"})
public class AppConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Student student(){
        return new Student("Zero", 0);
    }

    @Bean
    public Animal animal(){
        return new Animal();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Cat cat(){
        return new Cat();
    }
}
