package com.cherrishccl.coding.basics.algorithm;

/**
 * @Author cherrishccl
 * @Date 2020/7/22 14:59
 * @Version 1.0
 * @Description 简单选择排序
 */
public class SelectionSort implements Sort {
    @Override
    public void sort(int[] arr) {
        if(null == arr || arr.length <= 1){
            return;
        }
        for(int i = 0; i < arr.length - 1; i++){
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                swap(arr, i, minIndex);
            }
        }
    }
}
