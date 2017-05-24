package com.classTest.thread.army;

/**
 * Created by root on 17-2-15.
 */
public class KeyPersonThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始了战斗");
        for(int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"左突右杀，攻击隋军!");
        }

        System.out.println(Thread.currentThread().getName()+"结束了战斗");
    }
}
