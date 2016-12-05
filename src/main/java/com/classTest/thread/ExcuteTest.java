package com.classTest.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by root on 16-12-5.
 */
public class ExcuteTest {

    public static void main(String[] args) {
        System.out.println("----程序开始运行----");

        int taskSize = 5;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 1; i < taskSize + 1; i++) {
            Callable c = new MyCallable(i + " ");
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();

        try {
            // 获取所有并发任务的运行结果
            for (Future f : list) {
                // 从Future对象上获取任务的返回值，并输出到控制台
                System.out.println("executor" + f.get().toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}

class MyCallable implements Callable<Object> {
    private String name;

    MyCallable(String name) {
        this.name = name;
    }

    public Object call() {
        for (int i = 0; i < 100; i++) {
            System.out.println(name + " : " + i);
        }
        return name + " end";
    }

}
