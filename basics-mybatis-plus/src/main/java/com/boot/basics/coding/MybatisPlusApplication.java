package com.boot.basics.coding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author chencl
 * @Date 2021/7/6 11:29
 * @Version 1.0
 * @Description MybatisPlusApplication
 */
@EnableAsync
@MapperScan(basePackages = "com.boot.basics.coding.dao")
@SpringBootApplication
public class MybatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }
}
