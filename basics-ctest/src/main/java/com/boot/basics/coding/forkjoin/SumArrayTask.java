package com.boot.basics.coding.forkjoin;

import com.boot.basics.coding.GenerateArray;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author cherrishccl
 * @Date 2020/9/14 9:16
 * @Version 1.0
 * @Description
 */
public class SumArrayTask extends RecursiveTask<Integer> {
    private final static int THRESHOLD = GenerateArray.ARRAY_LENGTH / 10;
    //表示我们要实际统计的数组
    private int[] arr;
    //开始统计的下标
    private int fromIndex;
    //统计到哪里结束的下标
    private int toIndex;

    public SumArrayTask(int[] arr, int fromIndex, int toIndex) {
        this.arr = arr;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }
    @Override
    protected Integer compute() {
        if(toIndex - fromIndex < THRESHOLD){
            int count = 0;
            for(int i = fromIndex; i <= toIndex; i++) {
                //SleepTools.ms(1);
                count = count + arr[i];
            }
            return count;
        }else {
            int mid = (fromIndex+toIndex)/2;
            SumArrayTask left = new SumArrayTask(arr, fromIndex, mid);
            SumArrayTask right = new SumArrayTask(arr,mid+1, toIndex);
            invokeAll(left,right);
            return left.join()+right.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] arr = GenerateArray.generateArray();
        SumArrayTask task = new SumArrayTask(arr, 0, arr.length - 1);
        long start = System.currentTimeMillis();
        pool.invoke(task);
        System.out.println("Task is Running.....");

        System.out.println("The count is "+task.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
