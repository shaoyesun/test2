package com.classTest.thread;

/**
 * Created by root on 16-11-11.
 */
public class Test implements Runnable{

    private String threadName;

    public Test(String name) {
        this.threadName = name;
        System.out.println(threadName + " begin...");
    }

    public void run() {
        for(int i = 0; i < 1000; i++) {
            System.out.println(threadName + " : " + i);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Thread thread;

    public void start(Test t) {
        if(thread == null) {
            thread = new Thread(t);
            thread.start();
        }
    }

    public static void main(String[] args) {
        Test t1 = new Test("t1");
        Test t2 = new Test("t2");
        Test t3 = new Test("t3");
        t1.start(t1);
        t2.start(t2);
        t3.start(t3);
    }
}
