package com.boot.basics.coding.algorithm.leet.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * @Author cherrishccl
 * @Date 2021/2/1 17:00
 * @Version 1.0
 * @Description TwoSum
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 9, 2, 7, 6, 4, 0, 5, 3, 8};
//        solution1(arr, 17);
        solution2(arr, 17);
    }

    private static int[] solution1(int[] arr, int target){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(arr[i] + arr[j] == target){
                    return new int[]{arr[i], arr[j]};
                }
            }
        }
        return null;
    }

    private static int[] solution2(int[] arr, int target){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            if(map.containsKey(target - arr[i])){
                return new int[]{map.get(target - arr[i]), arr[i]};
            }
            map.put(arr[i], i);
        }
        return null;
    }
}
