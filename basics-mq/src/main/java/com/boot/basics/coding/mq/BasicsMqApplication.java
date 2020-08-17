package com.boot.basics.coding.mq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.boot.basics.coding.mq.rabbit.dao")
@SpringBootApplication
public class BasicsMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicsMqApplication.class, args);
	}

}
