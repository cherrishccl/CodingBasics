package com.boot.basics.coding.spring.imports;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 14:32
 * @Version 1.0
 * @Description
 * Spring官方在动态注册bean时，大部分套路其实是使用ImportBeanDefinitionRegistrar接口.
 * 所有实现了该接口的类都会被ConfigurationClassPostProcessor处理，
 * ConfigurationClassPostProcessor实现了BeanFactoryPostProcessor接口，
 * 所以ImportBeanDefinitionRegistrar中动态注册的bean是优先于依赖其的bean初始化的，也能被aop、validator等机制处理。
 * ImportBeanDefinitionRegistrar需要配合@Configuration和@Import注解，
 * @Configuration定义Java格式的Spring配置文件，@Import注解导入实现了ImportBeanDefinitionRegistrar接口的类
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * AnnotationMetadata: 当前类的注解信息
     * BeanDefinitionRegistry：BeanDefinition注册类
     * 通过调用BeanDefinitionRegistry接口的registerBeanDefinition()方法，可以将所有需要添加到容器中的bean注入到容器中。
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry){
//        boolean employee = registry.containsBeanDefinition("employee");
//        boolean department = registry.containsBeanDefinition("department");
        boolean employee = registry.containsBeanDefinition(Employee.class.getName());
        boolean department = registry.containsBeanDefinition(Department.class.getName());
        if (employee && department){
            BeanDefinition beanDefinition = new RootBeanDefinition(Company.class);
            registry.registerBeanDefinition("company", beanDefinition);
        }
    }
}
