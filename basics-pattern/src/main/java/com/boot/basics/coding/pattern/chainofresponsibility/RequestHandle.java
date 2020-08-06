package com.boot.basics.coding.pattern.chainofresponsibility;

/**
 * @Author cherrishccl
 * @Date 2020/8/6 17:34
 * @Version 1.0
 * @Description
 */
public interface RequestHandle {
    void handle(Request request);
}
