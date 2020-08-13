package com.boot.basics.coding;

/**
 * @Author cherrishccl
 * @Date 2020/8/12 14:07
 * @Version 1.0
 * @Description
 */
public class LongestSubPalindrome {
    private int index, len;

    public String longestPalindrome(String src) {
        if (src.length() < 2) {
            return src;
        }
        for (int i = 0; i < src.length() - 1; i++) {
            palindromeHelper(src, i, i);
            palindromeHelper(src, i, i + 1);
        }
        return src.substring(index, index + len);
    }
    public void palindromeHelper(String src, int l, int r) {
        while (l >= 0 && r < src.length() && src.charAt(l) == src.charAt(r)) {
            l--;
            r++;
        }
        if (len < r - l - 1) {
            index = l + 1;
            len = r - l - 1;
        }
    }
}
