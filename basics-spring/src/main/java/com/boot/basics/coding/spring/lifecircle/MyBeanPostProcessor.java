package com.boot.basics.coding.spring.lifecircle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author cherrishccl
 * @Date 2020/8/21 10:43
 * @Version 1.0
 * @Description
 *
 * postProcessBeforeInitialization方法会在bean实例化和属性设置之后，自定义初始化方法之前被调用，
 * 而postProcessAfterInitialization方法会在自定义初始化方法之后被调用。
 * 当容器中存在多个BeanPostProcessor的实现类时，会按照它们在容器中注册的顺序执行。
 * 对于自定义BeanPostProcessor实现类，还可以让其实现Ordered接口自定义排序
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("调用了postProcessBeforeInitialization方法，beanName = " + beanName + ", bean = " + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("调用了postProcessAfterInitialization，beanName = " + beanName + ", bean = " + bean);
        return bean;
    }
}
