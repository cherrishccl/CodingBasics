package com.boot.basics.coding;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @Author cherrishccl
 * @Date 2020/8/11 17:56
 * @Version 1.0
 * @Description
 */
public class StringTest {
    public static void main(String[] args) {
        String result = replaceBlankCharacter("Abc Def Hij");
        System.out.println(result);
        String[] srcs = new String[]{"nihao", "nihaoma", "nishuone"};
        result = maxCommonPrefix(srcs);
        System.out.println(result);

        // abcfe d abcfe
        System.out.println(createPalindromeStr("abcdefehfabc"));
        System.out.println(checkPalindrome("abcfedefcba"));
        System.out.println(checkPalindrome("莺啼岸柳弄春晴"));
        System.out.println(checkPalindrome("莺啼岸柳弄春晴 柳弄春晴夜月明 明月夜晴春弄柳 晴春弄柳岸啼莺"));
        System.out.println("-----------------");
        System.out.println(Character.isLetterOrDigit('中'));
        longestBrackets();
    }

    private static void longestBrackets(){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int cnt = 0, max = 0, i;
        for(i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                cnt++;
            }else {
                cnt--;
            }
            max = Math.max(max, cnt);
        }
        scanner.close();
        System.out.println(max);
    }
    /**
     * 最长回文子串序列
     * @param s
     * @return
     */
    private int longestPalindromeSubseq(String s) {
        int len = s.length();
        int [][] dp = new int[len][len];
        for(int i = len - 1; i>=0; i--){
            dp[i][i] = 1;
            for(int j = i+1; j < len; j++){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len-1];
    }

    /**
     * 最长回文子串
     * @param src
     * @return
     */
    private static String longestSubPalindrome(String src){
        return new LongestSubPalindrome().longestPalindrome(src);
    }

    /**
     * 验证是否是回文字符串
     * @param src
     * @return
     */
    private static boolean checkPalindrome(String src){
        if(null == src || src.length() < 1){
            return true;
        }
        int l = 0, r = src.length() - 1;
        while (l < r){
            // 从头和尾开始向中间遍历
            if(!Character.isLetterOrDigit(src.charAt(l))){
                // 字符不是字⺟和数字的情况
                l++;
            }else if(!Character.isLetterOrDigit(src.charAt(r))){
                // 字符不是字⺟和数字的情况
                r--;
            }else {
                if(src.charAt(l) != src.charAt(r)){
                    return false;
                }
                l++;
                r--;
            }
        }
        return true;
    }
    /**
     * 构造回文字符串 长度
     * @param src
     * @return
     */
    private static int createPalindromeStr(String src){
        if(null == src || src.length() < 1){
            return 0;
        }
        HashSet<Character> res = new HashSet<>();
        char[] chars = src.toCharArray();
        int count = 0;
        for(int i = 0; i < chars.length; i++){
            if(!res.contains(chars[i])){
                // 如果hashset没有该字符就保存进去
                res.add(chars[i]);
            }else {
                // 如果有,就让count++（说明找到了⼀个成对的字符），然后把该字符移除
                res.remove(chars[i]);
                count++;
            }
        }
        return res.isEmpty() ? count * 2 : count * 2 + 1;
    }

    /**
     * 最长公共前缀字符串
     * @param srcs
     * @return
     */
    private static String maxCommonPrefix(String[] srcs){
        if(!checkStrs(srcs)){
            return "";
        }
        int len = srcs.length;
        StringBuilder sb = new StringBuilder();
        // 给字符串数组的元素按照升序排序(包含数字的话，数字会排在前⾯)
        Arrays.sort(srcs);
        int m = srcs[0].length();
        int n = srcs[len - 1].length();
        int num = Math.min(m, n);
        for(int i = 0; i < num; i++){
            if(srcs[0].charAt(i) == srcs[len - 1].charAt(i)){
                sb.append(srcs[0].charAt(i));
            }else {
                break;
            }
        }
        return sb.toString();
    }
    private static boolean checkStrs(String[] strs) {
        boolean flag = false;
        if (strs != null) {
            // 遍历strs检查元素值
            for (int i = 0; i < strs.length; i++) {
                if (strs[i] != null && strs[i].length() != 0) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 用%20替换空格字符
     * @param src
     * @return
     */
    private static String replaceBlankCharacter(String src){
        if(null == src){
            return null;
        }
        int len = src.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++){
            char c = src.charAt(i);
            if(String.valueOf(c).equals(" ")){
                sb.append("%20");
            }else {
                sb.append(c);
            }
        }
        return sb.toString();


        // return str.replaceAll("\\s", "%20");
    }

}
