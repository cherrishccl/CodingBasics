package com.boot.basics.coding.mybatis.dao;

import lombok.ToString;

import java.io.Serializable;

/**
 * @Author cherrishccl
 * @Date 2020/8/24 10:51
 * @Version 1.0
 * @Description
 */
public class User implements Serializable {
    private static final long serialVersionUID = 821465757967990094L;
    private int id;
    private String userName;
    private int age;
    private double salary;
    private int maxSize;

    public User() {
    }

    public User(int id, String userName, int age, double salary, int maxSize) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.salary = salary;
        this.maxSize = maxSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", maxSize=" + maxSize +
                '}';
    }
}
