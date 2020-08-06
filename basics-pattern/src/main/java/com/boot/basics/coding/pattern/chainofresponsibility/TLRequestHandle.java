package com.boot.basics.coding.pattern.chainofresponsibility;

/**
 * @Author cherrishccl
 * @Date 2020/8/6 17:37
 * @Version 1.0
 * @Description
 */
public class TLRequestHandle implements RequestHandle{
    RequestHandle rh;

    public TLRequestHandle(RequestHandle rh) {
        this.rh = rh;
    }


    @Override
    public void handle(Request request) {
        if (request instanceof LeaveRequest) {
            System.out.println("要请假, 项目组长审批!");
        } else {
            rh.handle(request);
        }
    }
}
