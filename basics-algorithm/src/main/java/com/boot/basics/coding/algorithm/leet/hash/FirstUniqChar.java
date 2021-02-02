package com.boot.basics.coding.algorithm.leet.hash;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author cherrishccl
 * @Date 2021/2/1 17:16
 * @Version 1.0
 * @Description FirstUniqChar
 */
public class FirstUniqChar {
    public static void main(String[] args) {
        System.out.println(solution1("adfrjlajlgja;ajbnb"));
    }
    private static Character solution1(String src){
        Map<Character, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < src.length(); i++){
            map.put(src.charAt(i), map.getOrDefault(src.charAt(i), 0) + 1);
        }
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return null;
    }
}
