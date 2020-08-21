package com.boot.basics.coding.spring.lifecircle;

/**
 * @Author cherrishccl
 * @Date 2020/8/21 9:37
 * @Version 1.0
 * @Description
 */
public class Student {
    private String name;
    private Integer age;



    public void init(){
        System.out.println("init student");
    }
    public void destroy(){
        System.out.println("destroy student");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
        System.out.println("Student类的构造方法");
    }
}
