package com.boot.basics.coding.algorithm.leet;

import java.util.HashMap;

import java.util.Stack;

/**
 * @Author cherrishccl
 * @Date 2021/2/19 15:28
 * @Version 1.0
 * @Description
 */
public class LeetAlgorithm {

    private static int removeDuplicates(int[] nums) {
        if(null == nums || nums.length == 0){
            return 0;
        }
        int p = 0, q = 1;
        while (q < nums.length){
            if(nums[p] != nums[q]){
                nums[p+1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    private static boolean validBrackets(String src){
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(){{
            put('(', 1);put(')', 4);
            put('[', 2);put(']', 5);
            put('{', 3);put('}', 6);
        }};
        Stack<Integer> stack = new Stack<>();

        for(Character c : src.toCharArray()){
            int val = map.get(c);
            if(val >= 1 && val <= 3){
                stack.push(val);
            }else {
                int val2 = 0;
                if(!stack.isEmpty()){
                    val2 = stack.peek();
                    if(val2 + 3 == val){
                        stack.pop();
                    }else {
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }
    /**
     * 最长公共前缀
     * @param strs
     * @return
     */
    private static String longestCommonPrefix(String[] strs) {
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
