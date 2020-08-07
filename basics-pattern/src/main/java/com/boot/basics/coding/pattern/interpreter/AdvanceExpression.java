package com.boot.basics.coding.pattern.interpreter;

/**
 * @Author cherrishccl
 * @Date 2020/8/7 9:58
 * @Version 1.0
 * @Description
 */
public class AdvanceExpression extends Expression {
    @Override
    public void interpret(Context ctx) {
        System.out.println("This is advance");
    }
}
