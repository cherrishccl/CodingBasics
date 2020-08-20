package com.boot.basics.coding.spring.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 14:04
 * @Version 1.0
 * @Description
 * 其主要作用是收集需要导入的配置类，selectImports()方法的返回值就是我们向Spring容器中导入的类的全类名。
 * 如果该接口的实现类同时实现EnvironmentAware， BeanFactoryAware  ，BeanClassLoaderAware或者ResourceLoaderAware，
 * 那么在调用其selectImports方法之前先调用上述接口中对应的方法，
 * 如果需要在所有的@Configuration处理完在导入时可以实现DeferredImportSelector接口。
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("++++++++++++++++++++++++");
        importingClassMetadata.getAnnotationTypes().stream().forEach(System.out::println);
        //return new String[0];
        return new String[]{
          Role.class.getName(),
          User.class.getName()
        };
    }
}
