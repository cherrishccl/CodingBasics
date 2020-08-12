package com.boot.basics.coding;

import java.util.Arrays;

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
    }

    /**
     * 最长子字符串
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
