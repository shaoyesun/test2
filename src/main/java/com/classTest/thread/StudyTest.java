package com.classTest.thread;

/**
 * Created by root on 17-2-14. 设计4个线程，其中两个线程每次对j加1，另外两个线程对j每次减1
 */
public class StudyTest {
    private int j;

    public static void main(String[] args) {
        StudyTest st = new StudyTest();
        Inc inc = st.new Inc();
        Dec dec = st.new Dec();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(inc);
            t.start();
            t = new Thread(dec);
            t.start();
        }

    }

    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "-inc:" + j);
    }

    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + "-dec:" + j);
    }

    class Inc implements Runnable {
        public void run() {
            for (int i = 0; i < 100; i++) inc();
        }
    }

    class Dec implements Runnable {
        public void run() {
            for (int i = 0; i < 100; i++) dec();
        }
    }
}
