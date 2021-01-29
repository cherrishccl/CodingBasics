package com.boot.basics.coding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author cherrishccl
 * @Date 2021/1/28 17:30
 * @Version 1.0
 * @Description TimeTest
 */
public class TimeTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = sdf.parse("2021-01-29 18:00:00.000");
        System.out.println(date.getTime());

        for(int i = 80; i < 120; i++){
            //System.out.println(String.format("%03d",112));
            System.out.println(String.format("%03d",i));
        }
    }
}
