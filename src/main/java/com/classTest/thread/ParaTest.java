package com.classTest.thread;

/**
 * Created by root on 16-12-22.
 */
public class ParaTest extends Thread{

    private String names;
    private int age;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void progress(String name, int age) {
        System.out.println(name + " | " + age);
    }

    @Override
    public void run() {
       progress(getNames(), getAge());
    }

    public static void main(String[] args) {
        ParaTest p0 = new ParaTest();
        p0.setNames("thread0");
        p0.setAge(0);
        Thread t0 = new Thread(p0, "thread0");
        t0.start();
        ParaTest p1 = new ParaTest();
        p1.setNames("thread1");
        p1.setAge(1);
        Thread t1 = new Thread(p1, "thread1");
        t1.start();
    }
}
