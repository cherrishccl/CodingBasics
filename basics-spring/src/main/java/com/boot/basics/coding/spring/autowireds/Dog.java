package com.boot.basics.coding.spring.autowireds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author cherrishccl
 * @Date 2020/8/21 11:54
 * @Version 1.0
 * @Description
 */
@Component("dog1")
public class Dog {

    /*@Autowired
    public Dog(Cat cat){
        this.cat = cat;
        System.out.println("Dog构造");
    }*/

    public Dog(@Autowired Cat cat){
        this.cat = cat;
        System.out.println("Dog构造");
    }

    private Cat cat;

    public Cat getCat() {
        return cat;
    }

    /*@Autowired
    public void setCat(Cat cat) {
        this.cat = cat;
    }*/

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Dog{" +  "cat=" + cat + '}';
    }
}
