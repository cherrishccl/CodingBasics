package com.boot.basics.coding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author cherrishccl
 * @Date 2020/8/17 16:44
 * @Version 1.0
 * @Description -Xms100M -Xmx100M -XX:+PrintGC
 * taskList 一直被executor所引用，无法释放（有内存泄漏）
 */
public class FullGcTest {
    private static class CardInfo {
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;
        Date birthdate = new Date();

        public void m() {}
    }

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);

        for (;;){
            modelFit();
            Thread.sleep(100);
        }
    }

    private static void modelFit(){
        // askList 一直被executor所引用，无法释放（有内存泄漏）
        // ScheduledThreadPoolExecutor scheduleWithFixedDelay（每隔多少时间，固定执行任务）
        // 定时任务一直要引用taskList ， 即executor不被gc掉，其引用taskList也不会被gc掉
        //不断的内存泄漏，引发最后的内存溢出，即出现频繁Full gc, 但 gc不了，最后有OOM问题

        // 换个线程池，或者直接execute就行了，如下（即解决下内存泄漏） executor.exectue();
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(info -> {
            // do something
            executor.scheduleWithFixedDelay(() -> {
                //do sth with info
                info.m();

            }, 2, 3, TimeUnit.SECONDS);
        });
    }

    private static List<CardInfo> getAllCardInfo(){
        List<CardInfo> taskList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            CardInfo ci = new CardInfo();
            taskList.add(ci);
        }

        return taskList;
    }
}
