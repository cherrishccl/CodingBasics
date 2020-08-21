package com.boot.basics.coding.spring.scans;

import com.boot.basics.coding.spring.Person;
import org.springframework.context.annotation.*;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 10:22
 * @Version 1.0
 * @Description
 */
@Configuration
//@ComponentScan(basePackages = "com.boot.basics.coding.spring.scans.include")

// ANNOTATION：按照注解进行过滤, 排除哪些注解
/*@ComponentScan(basePackages = "com.boot.basics.coding.spring.scans.include",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)})*/

// ANNOTATION：按照注解进行过滤, 包含哪些注解, 需要禁用默认设置useDefaultFilters, 否则不生效
/*@ComponentScan(basePackages = "com.boot.basics.coding.spring.scans.include",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})}, useDefaultFilters = false)*/

// ASSIGNABLE_TYPE：按照给定的类型进行过滤, PersonDao类或其子类允许被加载
/*@ComponentScan(basePackages = "com.boot.basics.coding.spring.scans.include",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {PersonDao.class})}, useDefaultFilters = false)*/

// ASPECTJ：按照ASPECTJ表达式进行过滤
/*@ComponentScan(basePackages = "com.boot.basics.coding.spring.scans.include",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ASPECTJ, classes = {AspectJTypeFilter.class})}, useDefaultFilters = false)*/

// REGEX：按照正则表达式进行过滤
/*@ComponentScan(basePackages = "com.boot.basics.coding.spring.scans.include",
        includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, classes = {RegexPatternTypeFilter.class})}, useDefaultFilters = false)*/

// CUSTOM：按照自定义规则进行过滤
@ComponentScan(basePackages = "com.boot.basics.coding.spring.scans.include",
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = {CustomTypeFilter.class})}, useDefaultFilters = false)
public class AppConfig {
    @Bean
    public Person personX(){
        Person person = new Person();
        person.setName("personX");
        person.setAge(123);
        return person;
    }
}
