package com.boot.basics.coding.pattern.chainofresponsibility;

/**
 * @Author cherrishccl
 * @Date 2020/8/6 17:37
 * @Version 1.0
 * @Description
 */
public class HRRequestHandle implements RequestHandle {
    @Override
    public void handle(Request request) {
        if (request instanceof DimissionRequest) {
            System.out.println("要离职, 人事审批!");
        }
        System.out.println("请求完毕");
    }
}
