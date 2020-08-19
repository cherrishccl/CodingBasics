package com.boot.basics.coding;

import com.boot.basics.coding.closure.Action;
import com.boot.basics.coding.closure.Milk;

/**
 * @Author cherrishccl
 * @Date 2020/8/19 11:38
 * @Version 1.0
 * @Description
 */
public class ClosureTest {
    public static void main(String[] args) {
        // 闭包的价值在于可以作为函数对象或者匿名函数，持有上下文数据，作为第一级对象进行传递和保存。
        // 闭包广泛用于回调函数、函数式编程中
        // 在JAVA中，闭包是通过“接口+内部类”实现，JAVA的内部类也可以有匿名内部类
        // 简单理解：闭包能够将一个方法作为一个变量去存储，
        // 这个方法有能力去访问所在类的自由变量。
        /**
         * 通过调用Action的方法实现对Milk私有变量num进行修改。
         * 有时候觉得直接使用set方法也可以直接修改private变量，
         * 但是从现实生活中来说让人去执行喝牛奶的动作比牛奶自己动手喝来的合理一些。
         */
        Milk milk = new Milk();
        Action action = milk.haveMeal();
        action.drink();
        action.drink();

        milk.currentNum();
        // 闭包会导致资源不被回收，如上例，在main方法中将milk设为null，
        // 使用haveMeals继续调用drink方法仍然会喝掉一瓶牛奶，说明Milk对象并没有被释放掉。
        milk = null;
        System.out.println(milk);
        action.drink();
    }
}
