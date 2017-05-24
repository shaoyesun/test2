package com.classTest.thread.army;

/**
 * Created by root on 17-2-15.
 */
public class ArmyRunnable implements Runnable {

    //volatile保证了线程可以正确的读取其它线程写入的值(线程可见性)
    volatile boolean keepRunning = true;

    public void run() {
        while (keepRunning) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "进攻对方[" + i + "]");
                Thread.yield();//让出处理器时间
            }
        }
        System.out.println(Thread.currentThread().getName() + "结束了战斗!");
    }
}
