package com.boot.basics.coding.algorithm;

/**
 * @Author cherrishccl
 * @Date 2020/8/4 15:11
 * @Version 1.0
 * @Description
 */
public class Algorithm {
    /**
     *      2  [] [] []
     *     []  0  [] []
     *        []  1  []
     *           []  5
     *------------------------
     *     8 []  2   8
     */
    public static void main(String[] args) {
       int[] arr = new int[]{9, 9, 8, 8, 5, 5, 6, 10, 10, 11, 11};
       int res = numOddCount(arr);
        System.out.println(res);

        arr = new int[]{9, 9, 8, 8, 5, 5, 6, 10, 10, 11, 11, 19};
        numOddCount1(arr);
    }

    /**
     * 数组有两个个数出现奇数个, 其余偶数个
     * @param arr
     * @return
     */
    public static void numOddCount1(int[] arr){
        int xor = 0;
        for(int i = 0; i < arr.length; i++){
            xor ^= arr[i];
        }

        int xor1 = xor & (~xor + 1);

        int res = 0;
        for(int i = 0; i < arr.length; i++){
            if((arr[i] & xor1) == xor1){
                res ^= arr[i];
            }
        }
        System.out.println(res + ", " + (res^xor));
    }

    /**
     * 数组有一个数出现奇数个, 其余偶数个
     * @param arr
     * @return
     */
    public static int numOddCount(int[] arr){
        int num = 0;
        for(int i = 0; i < arr.length; i++){
            num ^= arr[i];
        }
        return num;
    }
}
