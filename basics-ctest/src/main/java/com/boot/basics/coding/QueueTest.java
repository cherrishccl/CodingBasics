package com.boot.basics.coding;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author cherrishccl
 * @Date 2020/8/19 15:30
 * @Version 1.0
 * @Description
 */
public class QueueTest {
    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();
    }

    private static void test2() throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        boolean flag = queue.offer("aaa");
        flag = queue.offer("bbb");
        flag = queue.offer("ccc");
        flag = queue.offer("ddd");
        flag = queue.offer("eee");
        flag = queue.offer("fff");

        // 调用peek方法, 返回元素, 不存在则抛出异常NoSuchElementException
        String value = queue.element();
        value = queue.peek();

        // 移除并返回队列中第一个元素
        value = queue.poll();

        // 调用poll方法, 返回元素, 不存在则抛出异常NoSuchElementException
        value = queue.remove();

        // 队列空后会阻塞
        value = queue.take();

        System.out.println(queue.size());
    }
    private static void test1() throws InterruptedException {
        // add 调用的super.add -> offer(子类实现的offer), 即最终调用的是offer, 对offer返回 boolean值判断 false抛出异常
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);
        boolean flag = queue.add("aaa");
        flag = queue.add("bbb");
        flag = queue.add("ccc");
        flag = queue.offer("ddd");

        // 无返回值
        queue.put("eee");
        // 队列满后会阻塞
        queue.put("111");

        // 队列满后报错: java.lang.IllegalStateException: Queue full
        //queue.add("fff");
        queue.offer("fff");
        // 返回false 不报错
        flag = queue.offer("ggg");



        System.out.println(queue.size());
    }
}
