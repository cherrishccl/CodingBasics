package com.boot.basics.coding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author cherrishccl
 * @Date 2020/8/7 17:22
 * @Version 1.0
 * @Description 线程按指定顺序执行
 */
public class ThreadTest1 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("111111111111111111111");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                    System.out.println("222222222222222222");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                    System.out.println("333333333333333");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t3.start();
        t2.start();
        t1.start();
        System.out.println("----------------------------------");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            System.out.println("AAAAAAAAAAAAAAA");
        });
        executorService.execute(() -> {
            System.out.println("BBBBBBBBBBBBBBB");
        });
        executorService.execute(() -> {
            System.out.println("CCCCCCCCCCCCCCC");
        });
    }

}
