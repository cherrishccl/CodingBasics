package com.boot.basics.coding.proxy.jdk;

/**
 * @Author cherrishccl
 * @Date 2020/8/14 14:02
 * @Version 1.0
 * @Description
 */
public class ShoppingServiceImpl implements ShoppingService{
    @Override
    public int[] buy() {
        return new int[]{1, 2};
    }

    @Override
    public void sale() {
        System.out.println("卖东西");
    }
}
