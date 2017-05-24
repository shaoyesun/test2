package com.classTest.thread;

/**
 * Created by root on 17-2-14.
 * 子线程循环10次，接着主线程循环100次，交替50次
 */
public class StudyTest1 {
    boolean b = true;
    public static void main(String[] args) {
        StudyTest1 s = new StudyTest1();
        Thread t = null;
        for(int i = 0; i < 20; i++) {
            t = new Thread(s.new SubThread());
            t.start();
            t = new Thread(s.new MainThread());
            t.start();
        }
    }

    class MainThread implements Runnable{
        public void run() {
            if(!b) {
                synchronized (this) {
                    for(int i = 0; i < 50; i++){
                        System.out.println("====main:i="+i);
                    }
                }
                b = true;
                notify();
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class SubThread implements Runnable{
        public void run() {
            if(b){
                synchronized (this){
                    for(int i = 0; i < 50; i++){
                        System.out.println("sub:i="+i);
                    }
                    b = false;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

