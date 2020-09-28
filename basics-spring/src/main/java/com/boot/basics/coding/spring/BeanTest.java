package com.boot.basics.coding.spring;

import org.springframework.beans.BeanUtils;

/**
 * @Author cherrishccl
 * @Date 2020/9/28 15:47
 * @Version 1.0
 * @Description
 */
public class BeanTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setAge(1);
        p1.setName("11");

        Person p2 = new Person();
        BeanUtils.copyProperties(p1, p2);

        p2.setName("2222");

        System.out.println(p1);
        System.out.println(p2);
    }
}
