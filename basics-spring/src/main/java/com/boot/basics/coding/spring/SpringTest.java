package com.boot.basics.coding.spring;

import com.boot.basics.coding.spring.context.Person;
import com.boot.basics.coding.spring.context.PersonConfig;
import com.boot.basics.coding.spring.scope.ThreadScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author cherrishccl
 * @Date 2020/8/20 10:20
 * @Version 1.0
 * @Description
 */
public class SpringTest {
    public static void main(String[] args) {
        // testXmlConfig();
        // testAnnotationConfig();
        // testXmlConfigScans();
        // testAnnotationConfigScans();
        // testScope();
        testScope1();
    }

    static void testScope1(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.scope.PersonConfig.class);
        //向容器中注册自定义的scope
        ctx.getBeanFactory().registerScope(ThreadScope.THREAD_SCOPE, new ThreadScope());
        //使用容器获取bean
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread() + "," + ctx.getBean(Person.class));
                System.out.println(Thread.currentThread() + "," + ctx.getBean(Person.class));
            }).start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static void testScope(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.scope.PersonConfig.class);
        String[] names = ctx.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
        System.out.println("------------------------");
        Person person1 = ctx.getBean(Person.class);
        Person person2 = ctx.getBean(Person.class);
        System.out.println(person1 == person2);
    }
    /**
     * @ComponentScan注解来指定Spring扫描哪些包，可以使用excludeFilters()指定扫描时排除哪些组件，
     * 也可以使用includeFilters()指定扫描时只包含哪些组件。
     * 当使用includeFilters()指定只包含哪些组件时，需要禁用默认的过滤规则
     */
    static void testAnnotationConfigScans(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.scans.PersonConfig.class);
        // Person person = (Person) ctx.getBean("personX");
        Person person = ctx.getBean(Person.class);
        System.out.println(person);
        System.out.println("------------------------");
        String[] names = ctx.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
    }
    static void testXmlConfigScans(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-scans.xml");
        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
        System.out.println("------------------------");
        String[] names = ctx.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
    }
    static void testAnnotationConfig(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(PersonConfig.class);
        Person person = ctx.getBean(Person.class);
        System.out.println(person);

        //按照类型找到对应的bean名称数组
        String[] names = ctx.getBeanNamesForType(Person.class);
        Arrays.stream(names).forEach(System.out::println);
    }
    static void testXmlConfig(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) ctx.getBean("person");
        System.out.println(person);
    }

}
