package com.classTest.thread;

/**
 * Created by root on 16-12-5.
 */
public class RunnableTest implements Runnable {

    private String name;

    public RunnableTest(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(name + " runnabel : " + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        RunnableTest rt1 = new RunnableTest("rt1");
        Thread t1 = new Thread(rt1);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RunnableTest rt2 = new RunnableTest("rt2");
        Thread t2 = new Thread(rt2);
        t2.start();
    }
}
