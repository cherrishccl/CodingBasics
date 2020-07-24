package com.boot.basics.coding.algorithm;

/**
 * @Author chencl
 * @Date 2020/7/23 13:53
 * @Version 1.0
 * @Description 插入排序
 */
public class InsertionSort implements Sort {
    @Override
    public void sort(int[] arr) {
        // 直接插入排序
//        straightSort(arr);
        binarySort(arr);
    }

    /**
     * 直接插入排序
     * @param arr
     */
    private void straightSort(int[] arr){
        if(null == arr || arr.length <= 1){
            return;
        }
        // 默认arr[0]有序
       for(int i = 1, j, temp; i < arr.length; i++){
           j = i - 1;
           temp = arr[i];
           for(; j >= 0 && arr[j] > temp; j--){
               // 移动而非交换
               arr[j + 1] = arr[j];
           }
           arr[j + 1] = temp;
       }
    }

    private void binarySort(int[] arr){
        int temp, left, middle, right;
        for(int i = 1; i < arr.length; i++){
            temp = arr[i];
            left = 0;
            right = i - 1;
            while (left <= right){
                middle = (left + right) / 2;
                if(arr[middle] > temp){
                    right = middle - 1;
                }else {
                    left = middle + 1;
                }
            }
            for(int j = i - 1; j >= left; j--){
                arr[j + 1] = arr[j];
            }
            arr[left] = temp;
        }
    }
}
