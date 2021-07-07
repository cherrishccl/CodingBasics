package com.boot.basics.coding.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Author cherrishccl
 * @Date 2020/8/24 10:51
 * @Version 1.0
 * @Description
 */
@TableName("t_user")
public class User implements Serializable {
    private static final long serialVersionUID = 821465757967990094L;
    @TableId(type = IdType.AUTO)
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
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
