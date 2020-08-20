package com.boot.basics.coding.spring.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 14:50
 * @Version 1.0
 * @Description
 */
@Configuration
public class PersonConfig {
    @Bean
    public PersonFactoryBean personFactoryBean(){
        return new PersonFactoryBean();
    }
}
