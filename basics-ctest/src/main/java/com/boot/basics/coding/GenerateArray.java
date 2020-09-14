package com.boot.basics.coding;

import java.util.Random;

/**
 * @Author cherrishccl
 * @Date 2020/9/14 9:15
 * @Version 1.0
 * @Description
 */
public class GenerateArray {
    //数组长度
    public static final int ARRAY_LENGTH  = 100000000;

    public static int[] generateArray() {

        //new一个随机数发生器
        Random r = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for(int i = 0; i < ARRAY_LENGTH; i++){
            //用随机数填充数组
            result[i] =  r.nextInt(ARRAY_LENGTH*3);
        }
        return result;

    }
}
