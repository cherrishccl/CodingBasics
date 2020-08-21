package com.boot.basics.coding.spring.autowireds;

import org.springframework.beans.factory.annotation.Autowired;
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
@ComponentScan(basePackages = {"com.boot.basics.coding.spring.autowireds"})
public class AppConfig {
    // cat = null
    /*@Bean
    public Fish fish(){
        return new Fish();
    }*/

    // cat != null
    /*@Bean
    public Fish fish(Cat cat){
        Fish fish = new Fish();
        fish.setCat(cat);
        return fish;
    }*/
    @Bean
    public Fish fish(@Autowired Cat cat){
        Fish fish = new Fish();
        fish.setCat(cat);
        return fish;
    }
}
