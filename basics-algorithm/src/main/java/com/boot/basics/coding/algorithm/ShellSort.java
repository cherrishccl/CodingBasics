package com.boot.basics.coding.algorithm;

/**
 * @Author cherrishccl
 * @Date 2020/8/3 15:58
 * @Version 1.0
 * @Description
 */
public class ShellSort implements Sort{
    @Override
    public void sort(int[] arr) {
        if(null == arr || arr.length < 1){
            return;
        }
        int gap = 1, len = arr.length;
        while (gap <= len / 3){
            gap = gap * 3 + 1;
        }

        while (gap > 0){
            for(int i = gap, j, temp; i < len; i++){
                temp = arr[i];
                for(j = i - gap; j >= 0 && arr[j] > temp; j -= gap){
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = temp;
            }

            gap /= 3;
        }
    }
}
