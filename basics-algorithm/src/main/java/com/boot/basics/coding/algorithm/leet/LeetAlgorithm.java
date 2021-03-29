package com.boot.basics.coding.algorithm.leet;

import com.boot.basics.coding.algorithm.listnode.ListNode;

import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;
import java.util.Stack;

/**
 * @Author cherrishccl
 * @Date 2021/2/19 15:28
 * @Version 1.0
 * @Description
 */
public class LeetAlgorithm {

    public static void main(String[] args) {
        ListNode<Integer> l1 = new ListNode<>(1);
        l1.next = new ListNode<>(2);
        l1.next.next = new ListNode<>(3);
        l1.next.next.next = new ListNode<>(4);
        ListNode<Integer> l2 = new ListNode<>(7);
        l2.next = new ListNode<>(8);
        l2.next.next = new ListNode<>(9);
        l2.next.next.next = new ListNode<>(10);
        l2.next.next.next.next = new ListNode<>(11);

        ListNode<Integer> l = mergeTwoListNodes(l1, l2);
        System.out.println(l.val);
        int[] arr = new int[]{3, 2, 2, 3};
        removeElement(arr, 3);
        System.out.println(Arrays.toString(arr));

        System.out.println(strStr("aaaabbb", "abc"));
    }

    public int strStr(String src, String dest, int x) {
        if (dest == null || dest.length() == 0) {
            return 0;
        }
        if (src == null || src.length() == 0 || dest.length() > src.length()) {
            return -1;
        }

        char[] string = src.toCharArray(), pattern = dest.toCharArray();
        int[] next = new int[pattern.length];
        next[0] = -1;
        for (int i = 1, k = -1; i < next.length;) {
            if (k == -1) {
                k = next[i] = pattern[0] == pattern[i++] ? 0 : -1;
            } else {
                k = pattern[i] == pattern[k + 1] ? next[i++] = k + 1 : next[k];
            }
        }

        for (int s = 0, p = 0; s < string.length; s++) {
            if (pattern[p] != string[s] && p != 0) {
                p = next[p - 1] + 1;
                s--;
            } else if (pattern[p] == string[s]) {
                if (++p == pattern.length) {
                    return s - p + 1;
                }
            }
        }
        return -1;
    }
    public static int strStr(String src, String dest){

        if (dest == null || dest.length() == 0) {
            return 0;
        }
        if (src == null || src.length() == 0 || dest.length() > src.length()) {
            return -1;
        }

        int first = -1;
        for(int i = 0; i < src.length(); i++){
            while (i < src.length() && src.charAt(i) != dest.charAt(0)){
                i++;
            }
            if(i < src.length()){
                int j = 0;
                if(src.charAt(i) == dest.charAt(0)){
                    first = i;
                }
                for(; j < dest.length(); j++){
                    if(src.length() > (first + j) && dest.charAt(j) != src.charAt(first + j)){
                        first = -1;
                        break;
                    }
                }
                if(j == dest.length()){
                    break;
                }
            }
        }
        return first;
    }
    public static int removeElement(int[] nums, int val) {
        if(null == nums || nums.length == 0){
            return 0;
        }
        int i = 0;
        for(int num : nums){
            if(num != val){
                nums[i] = num;
                i++;
            }
        }
        return i;
    }
    private static int removeDuplicatesArray(int[] arr){
        if(null == arr || arr.length == 0){
            return 0;
        }
        int p = 0, q = 1;
        while (q < arr.length){
            if(arr[p] != arr[q]){
                arr[p + 1] = arr[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    private static ListNode<Integer> mergeTwoListNodes(ListNode<Integer> l1, ListNode<Integer> l2){
        if(null == l1){
            return l2;
        }
        if(null == l2){
            return l1;
        }
        ListNode<Integer> dummy = new ListNode<Integer>(0);
        ListNode<Integer> cur = dummy;
        while (null != l1 && null != l2){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

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

    private static int[] twoSum(int[] arr, int target){
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        for(int i = 0; i < arr.length; i++){
            if(null != map.get(target - arr[i])){
                return new int[]{i, map.get(target - arr[i])};
            }
            map.put(arr[i], i);
        }
        return null;
    }
}
