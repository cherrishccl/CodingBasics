package com.boot.basics.coding;

import org.openjdk.jol.info.ClassLayout;

import java.time.LocalDate;

/**
 * @Author chencl
 * @Date 2021/6/24 14:54
 * @Version 1.0
 * @Description JolTest
 */
public class JolTest {
    private Integer num;
    private Long ll;
    private long l;
    private LocalDate[] ii;

    public LocalDate[] getII(){
        return ii;
    }
    public static void main(String[] args) {
        Object o = new Object();

        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        JolTest jolTest = new JolTest();

        System.out.println(ClassLayout.parseInstance(jolTest).toPrintable());
        System.out.println(ClassLayout.parseInstance(EnumTest.N).toPrintable());
    }


    public enum EnumTest{
        Y,N
    }
}
