package com.boot.basics.coding.pattern.chainofresponsibility;

/**
 * @Author cherrishccl
 * @Date 2020/8/6 17:37
 * @Version 1.0
 * @Description
 */
public class PMRequestHandle implements RequestHandle {
    RequestHandle rh;

    public PMRequestHandle(RequestHandle rh) {
        this.rh = rh;
    }

    @Override
    public void handle(Request request) {
        if (request instanceof SalaryRequest) {
            System.out.println("要加薪, 项目经理审批!");
        } else {
            rh.handle(request);
        }
    }

}
