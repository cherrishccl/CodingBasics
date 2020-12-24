package com.boot.basics.coding.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author cherrishccl
 * @Date 2020/12/24 13:39
 * @Version 1.0
 * @Description
 */
@Slf4j
public class SortTest {
    public static void main(String[] args) {
        int[] arr = new int[]{33, 66, 44, 33, 55, 99, 77, 88, 11, 22};
        log.info(Arrays.toString(arr));
        // bubble(arr);
        // selection(arr);
        insertion(arr);
        log.info(Arrays.toString(arr));
    }
    public static void shells(int[] arr){
        if(null == arr || arr.length < 2){
            return;
        }
    }
    public static void insertion(int[] arr){
        if(null == arr || arr.length < 2){
            return;
        }
        int i = 1, j, tmp;
        for(; i < arr.length; i++){
            tmp = arr[i];
            j = i - 1;
            for(; j >= 0 && arr[j] > tmp; j--){
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
    }
    public static void selection(int[] arr){
        if(null == arr || arr.length < 2){
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
                if(arr[i] > arr[minIndex]){
                    swap(arr, i, minIndex);
                }
            }
        }
    }
    public static void bubble(int[] arr){
        if(null == arr || arr.length < 2){
            return;
        }
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(arr[i] > arr[j]){
                    swap(arr, i, j);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static int[] generateArray(int length){
        int[] arr = new int[length];
        Random random = new Random(10);
        for(int i = 0; i < length; i++){
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }
}
