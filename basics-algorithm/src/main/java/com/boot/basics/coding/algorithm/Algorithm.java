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

        System.out.println(num());
        System.out.println(sqrt2());
        System.out.println(newtonSqrt2());
        System.out.println(newton(2D));
    }

    /**
     * 已知 sqrt (2)约等于 1.414，要求不用数学库，求 sqrt (2)精确到小数点后 10 位。
     * 1. 已知 sqrt(2)约等于 1.414，那么就可以在(1.4, 1.5)区间做二分
     * 2. 牛顿迭代法
     * @return
     */
    public static double sqrt2(){
        double epsilon = 1e-10;
        double low = 1.4D, high = 1.5D;
        double mid = (low + high) / 2;
        while (high - low < epsilon){
            if(mid * mid > 2){
                high = mid;
            }else {
                low = mid;
            }
            mid = (high + low) / 2;
        }
        return mid;
    }
    public static double newtonSqrt2(){
        double epsilon = 1e-10;
        double c = 2D;
        double x = c;
        double y = (x + c / x) / 2;
        while (x - y > epsilon){
            x = y;
            y = (x + c / x) / 2;
        }
        return x;
    }

    /**
     * 牛顿代入法： 求c的平方根, f(x) = x2 - c;
     * @param c
     * @return
     */
    public static double newton(double c){
        if(c < 0){
            return Double.NaN;
        }
        double e = 1e-10;
        double x = c;
        double y = (x + c / x) / 2;
        while (Math.abs(x - y) > e){
            x = y;
            y = (x + c / x) / 2;
        }
        return x;
    }

    /**
     * 一个数被2除余1,被3除余2,被4除余3,被5除余4,被6除余5
     * 即: 一个数+1, 能被2 3 4 5 6整除, 最小公倍数
     * 除2余1为奇数, 除5余4尾数为9
     */
    public static int num(){
        int res = 0;
        int i = 0;
        while (true){
            res = i * 10 + 9;
            if(res % 2 == 1 && res % 3 == 2 && res % 4 == 3 && res % 5 == 4 && res % 6 == 5){
                return res;
            }
            i++;
        }
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
