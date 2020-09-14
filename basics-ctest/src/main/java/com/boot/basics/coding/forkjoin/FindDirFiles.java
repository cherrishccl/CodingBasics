package com.boot.basics.coding.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @Author chencl
 * @Date 2020/9/14 9:26
 * @Version 1.0
 * @Description
 */
public class FindDirFiles extends RecursiveAction {
    private File path;

    public FindDirFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        List<FindDirFiles> subTasks = new ArrayList<>();

        File[] files = path.listFiles();
        if(files!=null) {
            for(File file:files) {
                if(file.isDirectory()) {
                    subTasks.add(new FindDirFiles(file));
                }else {
                    //遇到文件，检查
                    if(file.getAbsolutePath().endsWith("txt")) {
                        System.out.println("文件："+file.getAbsolutePath());
                    }
                }
            }
            if(!subTasks.isEmpty()) {
                for(FindDirFiles subTask:invokeAll(subTasks)) {
                    subTask.join();//等待子任务执行完成
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 用一个 ForkJoinPool 实例调度总任务
        ForkJoinPool pool = new ForkJoinPool();
        FindDirFiles task = new FindDirFiles(new File("F:/"));

        //异步调用
        pool.execute(task);

        System.out.println("Task is Running......");
        Thread.sleep(1);
        int otherWork = 0;
        for(int i=0;i<100;i++){
            otherWork = otherWork+i;
        }
        System.out.println("Main Thread done sth......,otherWork="+otherWork);
        task.join();//阻塞的方法
        System.out.println("Task end");
    }
}
