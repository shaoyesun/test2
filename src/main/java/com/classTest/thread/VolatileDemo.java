package com.classTest.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by root on 16-12-22.
 */
public class VolatileDemo {
    private AtomicInteger number = new AtomicInteger(0);

    public AtomicInteger getNumber() {
        return number;
    }

    public synchronized void increase(){
        number.addAndGet(1);
        System.out.println(number);
    }

    public static void main(String[] args) {
        final VolatileDemo vd = new VolatileDemo();
        for(int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                public void run() {
                    vd.increase();
                }
            }).start();
        }
        //保证所有线程执行完成再执行输出
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("number : " + vd.getNumber());
    }
}
