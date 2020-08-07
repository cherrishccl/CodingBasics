package com.boot.basics.coding.pattern.interpreter;

/**
 * @Author cherrishccl
 * @Date 2020/8/7 9:56
 * @Version 1.0
 * @Description
 */
public abstract class Expression {
    public abstract void interpret(Context ctx);
}
