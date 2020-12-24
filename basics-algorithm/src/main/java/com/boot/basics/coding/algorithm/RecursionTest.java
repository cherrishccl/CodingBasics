package com.boot.basics.coding.algorithm;

/**
 * @Author cherrishccl
 * @Date 2020/12/24 9:23
 * @Version 1.0
 * @Description
 */
public class RecursionTest {
    public static void main(String[] args) {
        int r = factorial(15);
        r = fibonacci(10);
        System.out.println(r);
    }

    public static int factorial(int n){
        if(n <= 2){
            return n;
        }
        return factorial(n - 1) * n;
    }

    public static int fibonacci(int n){
        if(n <= 2){
            return 1;
        }
        return fibonacci(n - 1) + factorial(n - 2);
    }
}
