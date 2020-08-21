package com.boot.basics.coding.spring.lifecircle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author cherrishccl
 * @Date 2020/8/21 10:28
 * @Version 1.0
 * @Description
 *
 * @PostConstruct注解被用来修饰一个非静态的void()方法。被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，
 * 并且只会被服务器执行一次。PostConstruct在构造函数之后执行，init()方法之前执行。
 *
 * 该注解的方法在整个Spring Bean初始化中的执行顺序：
 * Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)。
 *
 * @PreDestroy修饰的方法会在服务器卸载Servlet的时候运行，并且只会被服务器调用一次，类似于Servlet的destroy()方法。
 * 被@PreDestroy修饰的方法会在destroy()方法之后运行，在Servlet被彻底卸载之前。
 *
 * 执行顺序如下所示：
 * 调用destroy()方法->@PreDestroy->destroy()方法->bean销毁。
 *
 * @see org.springframework.context.annotation.CommonAnnotationBeanPostProcessor
 * 构造方法 -> @PostConstruct -> init()方法 -> @PreDestroy -> destroy()方法。
 */
public class Cat {

    public Cat(){
        System.out.println("Cat类的构造方法...");
    }

    public void init(){
        System.out.println("Cat的init()方法...");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Cat的postConstruct()方法...");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("Cat的preDestroy()方法...");
    }

    public void destroy(){
        System.out.println("Cat的destroy()方法...");
    }
}
