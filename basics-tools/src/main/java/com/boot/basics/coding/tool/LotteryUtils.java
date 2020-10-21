package com.boot.basics.coding.tool;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author cherrishccl
 * @Date 2020/10/21 17:54
 * @Version 1.0
 * @Description
 */
public class LotteryUtils {
    public static void main(String[] args) {
        generateLotterySSQ1();
        generateLotterySSQ2();
    }

    public static void generateLotterySSQ1() {
        Random rd = new Random();
        //方法二:普通方法
        int x[] = new int[6];
        int i;
        int l, index = 0, num;
        //随机产生0到15的整数，+ 1，随机产生1到16的整数
        l = rd.nextInt(16) + 1;
        int con;
        while (x.length > index) {
            num = rd.nextInt(33) + 1;
            con = isIn(x, num);
            if (con == 1) {
                x[index] = num;
                index++;
            }

        }

        //排序
        System.out.println("开奖号码:");
        Arrays.sort(x);//从小到大排列的方法
        System.out.print("红球:");
        for (i = 0; i < x.length; i++) {
            System.out.printf("%02d\t", x[i]);//格式输出0表示指定空位填0
        }
        System.out.print("蓝球: ");
        System.out.printf("%02d\n", l);


    }

    public static void generateLotterySSQ2() {
        int i, j;
        Random rd = new Random();
        int x[] = new int[6];
        int a;
        int l;
        l = rd.nextInt(16) + 1;
        for (i = 0; i < x.length; i++) {
            a = rd.nextInt(33) + 1;
            x[i] = a;
            for (j = 0; j < i; j++) {
                if (x[i] == x[j]) {
                    a = rd.nextInt(33) + 1;
                    x[i] = a;
                }
            }
        }

        //排序
        System.out.println("开奖号码:");
        Arrays.sort(x);
        System.out.print("红球:");
        for (i = 0; i < x.length; i++) {
            System.out.printf("%02d\t", x[i]);
        }
        System.out.print("蓝球: ");
        System.out.printf("%02d\n", l);
    }

    public static int isIn(int array[], int n) {
        int i;
        for (i = 0; i < array.length; i++) {
            if (n == array[i]) {
                return 0;
            }
        }
        return 1;
    }
}
