package com.boot.basics.coding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author cherrishccl
 * @Date 2021/1/12 14:49
 * @Version 1.0
 * @Description CommonTest
 */
public class CommonTest {
    private static final int count = 10000;
    private static final Long[] timemills = new Long[count];
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for(int i = 0; i < count; i++){
            service.execute(new GetTimeMillis(i));
        }
        service.shutdown();

        TimeUnit.SECONDS.sleep(10L);
        HashSet<Long> s = new HashSet<>();
        for(int i = 0; i < timemills.length; i++){
            System.out.println(timemills[i]);
            s.add(timemills[i]);
        }
        System.out.println(timemills.length);
        System.out.println(s.size());

    }

    static class GetTimeMillis implements Runnable{
        private int i;
        public GetTimeMillis(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            timemills[i] = System.currentTimeMillis();
        }
    }
}
