package com.cherrishccl.coding.basics.algorithm;

/**
 * @Author cherrishccl
 * @Date 2020/7/22 14:23
 * @Version 1.0
 * @Description 冒泡排序
 */
public class BubbleSort implements Sort{
    @Override
    public void sort(int[] arr) {
        if(null == arr || arr.length <= 1){
            return;
        }
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i + 1; j < arr.length; j++){
                swap(arr, i, j);
            }
        }
    }
}
