package com.boot.basics.coding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @Author cherrishccl
 * @Date 2021/9/7 9:57
 * @Version 1.0
 * @Description LocalDateTimeFormatTest
 */
public class LocalDateTimeFormatTest {
    public static void main(String[] args) {
        /*
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDateTime 转 string
        System.out.println(formatter2.format(LocalDateTime.now()));
        // string 转 LocalDateTime
        System.out.println(LocalDateTime.parse("2021-09-07", formatter2));
*/
//        errorTest1();
        errorTest2();
    }

    private static void errorTest1(){
        DateTimeFormatter errFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // LocalDate 转 string, 报错: Unsupported field: HourOfDay
        System.out.println(errFormatter.format(LocalDate.now()));
        // string 转 LocalDate, 报错: Text '2021-09-07 10:28:30' could not be parsed, unparsed text found at index 10
        // 注意此处parse参数
        System.out.println(LocalDate.parse("2021-09-07 10:28:30"));
    }

    private static void errorTest2(){
        DateTimeFormatter errFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // LocalDateTime 转 string, 报错: Text '2021-09-07 10:28:30' could not be parsed, unparsed text found at index 10
        // System.out.println(errFormatter.format(LocalDateTime.now()));
        // string 转 LocalDate, 报错: Text '2021-09-07 10:28:30' could not be parsed at index 10
        System.out.println(LocalDateTime.parse("2021-09-07 10:28:30"));
        // string 转 LocalDate, 报错: Text '2021-09-07 10:28:30' could not be parsed, unparsed text found at index 10
        System.out.println(LocalDateTime.parse("2021-09-07 10:28:30", errFormatter));
    }


    private static void rightTest(){
        DateTimeFormatter errFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // LocalDate 转 string, 报错: Unsupported field: HourOfDay
        System.out.println(errFormatter1.format(LocalDate.now()));
        // string 转 LocalDate, 报错: Text '2021-09-07 10:28:30' could not be parsed, unparsed text found at index 10
        System.out.println(LocalDate.parse("2021-09-07 10:28:30"));
    }
}
