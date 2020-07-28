package com.boot.basics.coding.spring.cloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({Subscriber.class})
public class BasicsSpringCloudStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicsSpringCloudStreamApplication.class, args);
	}

}
