package com.boot.basics.coding.spring.lifecircle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @Author cherrishccl
 * @Date 2020/8/21 10:18
 * @Version 1.0
 * @Description
 * Spring为bean提供了两种初始化bean的方式，实现InitializingBean接口，实现afterPropertiesSet方法，
 * @see org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#invokeInitMethods(String, Object, RootBeanDefinition)  
 * 或者在配置文件和@Bean注解中通过init-method指定，两种方式可以同时使用。
 * 实现InitializingBean接口是直接调用afterPropertiesSet()方法，比通过反射调用init-method指定的方法效率相对来说要高点。
 * 但是init-method方式消除了对Spring的依赖。
 *
 * 即：
 * Spring为bean提供了两种初始化的方式，第一种实现InitializingBean接口，实现afterPropertiesSet方法，
 * 第二种配置文件或@Bean注解中通过init-method指定，
 * 两种方式可以同时使用，同时使用先调用afterPropertiesSet方法，后执行init-method指定的方法。
 * 如果调用afterPropertiesSet方法时出错，则不调用init-method指定的方法。
 */
public class Animal implements InitializingBean, DisposableBean {
    public Animal(){
        System.out.println("执行了Animal类的无参数构造方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行了Animal类的初始化方法。。。。。");

    }
    @Override
    public void destroy() throws Exception {
        System.out.println("执行了Animal类的销毁方法。。。。。");

    }
}
