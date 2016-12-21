package com.classTest.thread;

/**
 * Created by root on 16-12-5.
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        final Outputter output = new Outputter();
        new Thread() {
            public void run() {
                output.output("Thread ");
            }
        }.start();
        new Thread() {
            public void run() {
                output.output("synchronized ");
            }
        }.start();
    }
}

class Outputter {
    public synchronized void output(String name) {
        /*synchronized (this) {*/
        for (int i = 0; i < name.length(); i++) {
            System.out.print(name.charAt(i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*}*/
    }
}