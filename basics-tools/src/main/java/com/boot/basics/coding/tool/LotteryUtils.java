package com.boot.basics.coding.tool;

import java.util.*;

/**
 * @Author cherrishccl
 * @Date 2020/10/21 17:54
 * @Version 1.0
 * @Description
 */
public class LotteryUtils {
    public static void main(String[] args) {
        generateLotterySSQ1();
        System.out.println();
        generateLotterySSQ2();
        System.out.println();
        generateLotteryDLT();
    }

    public static void generateLotteryDLT() {
        //前区list集合
        List<Integer> pre = new ArrayList<>();
        //后区list集合
        List<Integer> post = new ArrayList<>();
        //前区1-35，随机五个不重复
        while (pre.size() < 5) {
            int a = (int) (Math.random() * 35 + 1);
            if (!pre.contains(a)) {
                pre.add(a);
            }
        }
        //按升序排列
        Collections.sort(pre);
        printArray(pre, ", ");
        //后区1-12随机2个不重复
        while (post.size() < 2) {
            int b = (int) (Math.random() * 12 + 1);
            if (!post.contains(b)) {
                post.add(b);
            }
        }
        //按升序排列
        Collections.sort(post);
        printArray(post, ", ");
    }

    public static void generateLotterySSQ1() {
        Random rd = new Random();
        //方法二:普通方法
        int[] red = new int[6];
        int i;
        int[] blue = new int[]{rd.nextInt(16) + 1};
        int index = 0, num;
        //随机产生0到15的整数，+ 1，随机产生1到16的整数
        int con;
        while (red.length > index) {
            num = rd.nextInt(33) + 1;
            con = isIn(red, num);
            if (con == 1) {
                red[index] = num;
                index++;
            }
        }

        //排序
        System.out.println("lottery:");
        Arrays.sort(red);
        System.out.print("red:");
        printArray(red, ", ");
        System.out.print("blue: ");
        printArray(blue, ", ");


    }

    public static void generateLotterySSQ2() {
        int i, j;
        Random rd = new Random();
        int[] red = new int[6];
        int a;
        int[] blue = new int[]{rd.nextInt(16) + 1};
        for (i = 0; i < red.length; i++) {
            a = rd.nextInt(33) + 1;
            red[i] = a;
            for (j = 0; j < i; j++) {
                if (red[i] == red[j]) {
                    a = rd.nextInt(33) + 1;
                    red[i] = a;
                }
            }
        }

        //排序
        System.out.println("lottery:");
        Arrays.sort(red);
        System.out.print("red:");
        printArray(red, ", ");
        System.out.print("blue: ");
        printArray(blue, ", ");
    }

    private static int isIn(int array[], int n) {
        int i;
        for (i = 0; i < array.length; i++) {
            if (n == array[i]) {
                return 0;
            }
        }
        return 1;
    }

    private static void printArray(int[] arr, String delimiter) {
        if(null == delimiter){
            delimiter = "\t";
        }
        System.out.printf("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%02d", arr[i]);
            if (i != arr.length - 1) {
                System.out.printf(delimiter);
            }
        }
        System.out.printf("]");
    }
    private static void printArray(List<Integer> list, String delimiter) {
        if(null == delimiter){
            delimiter = "\t";
        }
        System.out.printf("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%02d", list.get(i));
            if (i != list.size() - 1) {
                System.out.printf(delimiter);
            }
        }
        System.out.printf("]");
    }
}
