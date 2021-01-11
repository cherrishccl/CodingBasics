package com.boot.basics.coding;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
        AtomicBoolean flag = new AtomicBoolean(false);
        AtomicInteger at = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(100000);
        ExecutorService service = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10000; i++){
            service.submit(() -> {

                int num = at.getAndIncrement();
                try{
                    TimeUnit.MILLISECONDS.sleep(5);
                    if(flag.get()){
                        return;
                    }
                    if(num == 100){
                        int x = 100 / 0;
                    }
                    System.out.println(num);
                }catch (Exception e){
                    flag.set(true);
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                    System.out.println("-----------" + latch.getCount());
                }


            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------------" + at.get());

        List<String> status = Arrays.asList("aaa", "bbb", "ccc");
        status.parallelStream().forEach(str -> {
            System.out.println(Thread.currentThread().getName() + " " + str);
        });

    }

}
