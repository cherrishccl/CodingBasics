package com.boot.basics.coding;

/**
 * @Author cherrishccl
 * @Date 2021/11/9 16:50
 * @Version 1.0
 * @Description SuperClass
 */
public class SuperClass {
    protected void printSuperClassName(){
        System.out.println("SuperClass: " + this.getClass().getName());
    }
}
