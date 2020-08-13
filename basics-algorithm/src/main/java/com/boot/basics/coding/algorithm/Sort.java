package com.boot.basics.coding.algorithm;

/**
 * @Author cherrishccl
 * @Date 2020/7/22 14:34
 * @Version 1.0
 * @Description
 */
public interface Sort {
    /**
     * 排序
     *
     * @param arr
     */
    void sort(int[] arr);

    /**
     * 交换
     *
     * @param arr
     * @param i
     * @param j
     */
    default void swap(int[] arr, int i, int j) {
        if (null != arr && arr.length > 0) {
            /*int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;*/

                /*arr[i] = arr[i] + arr[j];
                arr[j] = arr[i] - arr[j];
                arr[i] = arr[i] - arr[j];*/

            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }
}
