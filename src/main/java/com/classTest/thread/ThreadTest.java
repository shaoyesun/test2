package com.classTest.thread;

/**
 * Created by root on 16-12-5.
 */
public class ThreadTest extends Thread {

    private String name;

    public ThreadTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("返回当前线程的线程组中活动线程的数目 " + name + " : " + Thread.activeCount());
        System.out.println("返回对当前正在执行的线程对象的引用 " + name + " : " + Thread.currentThread());
        try {
            for (int i = 0; i < 100; i++) {
                Thread.currentThread().sleep(1);
                System.out.println(name + " thread : " + i);
                //Thread.currentThread().interrupt();//中断线程
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadTest tt1 = new ThreadTest("tt1");
        tt1.setName("t1");//改变线程名称，使之与参数 name 相同
        tt1.start();//使该线程开始执行；Java 虚拟机调用该线程的 run 方法

        System.out.println("返回该线程的标识符 tt1 : " + tt1.getId());
        System.out.println("返回该线程的名称 tt1 : " + tt1.getName());
        System.out.println("返回线程的优先级 tt1 : " + tt1.getPriority());
        System.out.println("返回该线程的状态 tt1 : " + tt1.getState());

        System.out.println("测试线程是否处于活动状态 tt1 : " + tt1.isAlive());
        System.out.println("测试当前线程是否已经中断 tt1 : " + tt1.isInterrupted());
        System.out.println("测试该线程是否为守护线程 tt1 : " + tt1.isDaemon());

        ThreadTest tt2 = new ThreadTest("tt2");
        tt2.setName("t2");
        System.out.println("返回线程的优先级 tt2 : " + tt2.getPriority());
        tt1.yield();//暂停当前正在执行的线程对象，并执行其他线程
        try {
            //tt1.join();//等待该线程终止
            tt1.join(10);//等待该线程终止的时间最长为 millis 毫秒
            //tt1.join(10, 10);//等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tt2.start();
    }
}
