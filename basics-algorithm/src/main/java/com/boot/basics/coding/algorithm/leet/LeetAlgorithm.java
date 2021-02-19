package com.boot.basics.coding.algorithm.leet;

/**
 * @Author cherrishccl
 * @Date 2021/2/19 15:28
 * @Version 1.0
 * @Description
 */
public class LeetAlgorithm {
    /**
     * 最长公共前缀
     * @param strs
     * @return
     */
    private String longestCommonPrefix(String[] strs) {
        if(null == strs || strs.length == 0){
            return "";
        }
        String res = strs[0];
        for(String str : strs){
            while (!str.startsWith(res)){
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }
    /**
     * 罗马数字转整数
     * @param s
     * @return
     */
    private static int romanToInt(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private static int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
    /**
     * 回文数
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        // 123454321
        if(x < 0){
            return false;
        }
        int cur = x;
        int res = 0;
        while (x != 0){
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res == cur;
    }
    /**
     * 反转
     * @param x
     * @return
     */
    private static int reverse(int x){
        long res = 0;
        while (x != 0){
            res = res * 10 + x % 10;
            x /= 10;
        }
        return (int) res == res ? (int) res : 0;
    }
}
