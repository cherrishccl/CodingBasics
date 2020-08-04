package com.boot.basics.coding.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        List<List<Integer>> ones = new ArrayList<>();
        for(int a = 0; a < 10; a++){
            for(int b = 0; b < 10; b++){
                for(int c = 0; c < 10; c++){
                    if(a != b && b != c && a != c){
                        if((a + b + c) % 10 == 8){
                            ones.add(Arrays.asList(a, b, c));
                            System.out.println(a + "\t" + b + "\t" + c);
                        }
                    }
                }
            }
        }
        List<List<Integer>> tens = new ArrayList<>();
        for(int a = 0; a < 10; a++){
            for(int b = 0; b < 10; b++){
                for(int c = 0; c < 10; c++){
                    if(a != b && b != c && a != c){
                        for(List<Integer> one : ones){
                            if(!one.contains(a) && !one.contains(b) && !one.contains(c)){
                                int temp = (one.get(0) + one.get(1) + one.get(2)) / 10;
                                if((temp + a + b + c + 1) % 10 == 2){
                                    tens.add(Arrays.asList(a, b, c, one.get(0), one.get(1), one.get(2)));
                                    //System.out.println(a + "\t" + b + "\t" + c + "\t" + one.get(0) + "\t" + one.get(1) + "\t" + one.get(2));
                                }
                            }
                        }
                    }
                }
            }
        }

        List<List<Integer>> hans = new ArrayList<>();
        for(int a = 0; a < 10; a++){
            for(int b = 0; b < 10; b++){
                if(a != b){
                    for(List<Integer> temps : tens){
                        int one = (temps.get(3) + temps.get(4) + temps.get(5)) / 10;
                        int ten = (temps.get(0) + temps.get(1) + temps.get(2) + 1 + one) / 10;
                        if(!temps.contains(a) && !temps.contains(b)){
                            int h = (ten + a + b) % 10;
                            if(!temps.contains(h) && a != h && b != h){
                                hans.add(Arrays.asList(a, b, h, temps.get(0), temps.get(1), temps.get(2), temps.get(3), temps.get(4), temps.get(5)));
                                //System.out.println(a + "\t" + b + "\t" + h + "\t" + temps.get(0) + "\t" + temps.get(1) + "\t" + temps.get(2) + "\t" + temps.get(3) + "\t" + temps.get(4) + "\t" + temps.get(5));
                            }
                        }
                    }
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int a = 1; a < 10; a++){
            for(List<Integer> hs : hans){
                if(!hs.contains(a)){
                    int one = (hs.get(6) + hs.get(7) + hs.get(8)) / 10;
                    int ten = (hs.get(3) + hs.get(4) + hs.get(5) + 1 + one) / 10;
                    int han = (hs.get(0) + hs.get(1) + ten + 0) / 10;
                   // System.out.println("one = " + one + ",\t"+"ten = "+ ten + ",\t"+ "han = " + han);
                    /*if(a + han == 6){
                        System.out.println("*******************");
                    }*/
                    //System.out.println("--------------------------------" + a);
                    //int h = (hs.get(0) + hs.get(1) + hs.get(2) + ten + 0) % 10;
                    if(/*!hs.contains(h) && h != a &&*/ (a + han + 2) == 8){
                        System.out.println("-----------------------------------");
                        int[] arr = new int[]{a, hs.get(0), hs.get(1), hs.get(2), hs.get(3), hs.get(4), hs.get(5), hs.get(6), hs.get(7), hs.get(8)};
                        res.add(Arrays.asList(a, hs.get(0), hs.get(1), hs.get(2), hs.get(3), hs.get(4), hs.get(5), hs.get(6), hs.get(7), hs.get(8)));
                        System.out.println(Arrays.toString(arr));
                    }

                }
            }
        }

        List<Integer> all = new ArrayList<>();
        for(List<Integer> r : res){
            all.add(2 * 1000 + r.get(1) * 100 + r.get(4) * 10 + r.get(7));
        }
        Collections.sort(all);
        for(Integer i : all){
            System.out.println("---------->" + i);
        }
    }
}
