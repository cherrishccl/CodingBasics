package com.boot.basics.coding.apollo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.EnumerablePropertySource;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @Author cherrishccl
 * @Date 2020/7/24 9:47
 * @Version 1.0
 * @Description
 */
@SpringBootApplication
public class ApolloClientApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ApolloClientApplication.class, args);
    }

    @Autowired
    org.springframework.core.env.ConfigurableEnvironment environment;

    @Override
    public void run(String... args) throws Exception {
        TreeMap map = new TreeMap();
        StreamSupport.stream(environment.getPropertySources().spliterator(), false)
                .filter(propertySource -> (propertySource instanceof EnumerablePropertySource))
                .map(propertySource -> ((EnumerablePropertySource) propertySource).getPropertyNames())
                .flatMap(Arrays::stream).distinct().collect(Collectors.toMap(Function.identity(), environment::getProperty))
                .entrySet().stream().forEach(entry -> {
            //log.info("-------- {} ===> {}", entry.getKey(), entry.getValue());
            map.put(entry.getKey(), entry.getValue());
        });
        map.keySet().stream().forEach(key -> {
//            log.info("-------- {} ===> {}", key, map.get(key));
            System.out.println("-------- "+ key +" ===> " + map.get(key));
        });
    }
}
