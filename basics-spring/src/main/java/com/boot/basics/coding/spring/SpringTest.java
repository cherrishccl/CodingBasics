package com.boot.basics.coding.spring;

import com.boot.basics.coding.spring.autowireds.Cat;
import com.boot.basics.coding.spring.autowireds.Dog;
import com.boot.basics.coding.spring.autowireds.Fish;
import com.boot.basics.coding.spring.context.AppConfig;
import com.boot.basics.coding.spring.scope.ThreadScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Map;
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
        // testScope1();
        // testLazy();
        // testCondition();
        // testImport();
        // testFactoryBean();
        // testLifeCircle();
        testAutowired();
    }

    static void testAutowired(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.autowireds.AppConfig.class);
        System.out.println("IOC容器创建完成");
//        String[] names = ctx.getBeanDefinitionNames();
//        Arrays.stream(names).forEach(System.out::println);
        Dog dog = ctx.getBean(Dog.class);
        System.out.println(dog);
        Cat cat = ctx.getBean(Cat.class);
        System.out.println(cat);
        Fish fish = ctx.getBean(Fish.class);
        System.out.println(fish);
        ctx.close();
    }

    static void testLifeCircle(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.lifecircle.AppConfig.class);
        System.out.println("IOC容器创建完成");
        String[] names = ctx.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
        ctx.close();
    }
    static void testFactoryBean(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.factory.AppConfig.class);
        System.out.println("IOC容器创建完成");
        String[] names = ctx.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
        // Object personFactoryBean = ctx.getBean("personFactoryBean");
        // System.out.println("personFactoryBean实例的类型为：" + personFactoryBean.getClass());
        Object personFactoryBean1 = ctx.getBean("personFactoryBean");
        Object personFactoryBean2 = ctx.getBean("personFactoryBean");
        System.out.println("personFactoryBean1类型：" + personFactoryBean1.getClass());
        System.out.println("personFactoryBean2类型：" + personFactoryBean2.getClass());
        System.out.println(personFactoryBean1 == personFactoryBean2);

        Object personFactoryBean3 = ctx.getBean("&personFactoryBean");
        System.out.println("personFactoryBean3类型：" + personFactoryBean3.getClass());
    }

    static void testImport(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.imports.AppConfig.class);
        System.out.println("IOC容器创建完成");
        String[] names = ctx.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println);
    }
    static void testCondition(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.condition.AppConfig.class);
        System.out.println("IOC容器创建完成");

        Environment environment = ctx.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println(osName);
        String[] names = ctx.getBeanNamesForType(Person.class);
        Arrays.stream(names).forEach(System.out::println);

        Map<String, Person> beans = ctx.getBeansOfType(Person.class);
        System.out.println(beans);

    }
    static void testLazy(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.lazy.AppConfig.class);
        System.out.println("IOC容器创建完成");
        Person person = ctx.getBean(Person.class);
        System.out.println(person);

    }
    static void testScope1(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.scope.AppConfig.class);
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
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.scope.AppConfig.class);
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
        ApplicationContext ctx = new AnnotationConfigApplicationContext(com.boot.basics.coding.spring.scans.AppConfig.class);
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
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
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
