package com.boot.basics.coding;

/**
 * @Author cherrishccl
 * @Date 2020/9/2 9:55
 * @Version 1.0
 * @Description
 */
public class NullTest {
    public static void main(String[] args) {
        String str = null;
        String result = str + "";
        System.out.println("---------------" + result + "----------------");
        StringBuilder sb = new StringBuilder();
        sb.append(str).append("");

        System.out.println(sb.toString());
    }
}
