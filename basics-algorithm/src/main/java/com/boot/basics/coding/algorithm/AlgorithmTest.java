package com.boot.basics.coding.algorithm;

import java.util.Arrays;

/**
 * @Author chencl
 * @Date 2020/7/22 14:44
 * @Version 1.0
 * @Description
 */
public class AlgorithmTest {
    public static void main(String[] args) {
        int[] arr = new int[]{12, 99, 90, 89, 67, 78, 56, 45, 34, 23, 11, 98, 66, 33, 55, 22, 77, 44, 33, 56, 67, 43, 88};
//        Sort sort = new BubbleSort();
//        Sort sort = new SelectionSort();
        Sort sort = new InsertionSort();
        long start = System.nanoTime();
        sort.sort(arr);
        long end = System.nanoTime();
        System.out.println(Arrays.toString(arr));
        System.out.println("耗时: "+ (end - start) + "ns");
    }
}
