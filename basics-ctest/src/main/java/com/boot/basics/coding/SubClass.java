package com.boot.basics.coding;

/**
 * @Author cherrishccl
 * @Date 2021/11/9 16:50
 * @Version 1.0
 * @Description SubClass
 */
public class SubClass extends SuperClass{
    protected void printSubClassName(){
        System.out.println("SubClass:" + this.getClass().getName());
        System.out.println("SubClass---:" + super.getClass().getName());
    }
}
