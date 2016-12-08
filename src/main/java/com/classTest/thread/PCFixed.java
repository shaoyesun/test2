package com.classTest.thread;

/**
 * Created by root on 16-12-6.
 */
public class PCFixed {

    /**
     * 生产者消费者问题
     */
    public static void main(String args[]) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);
        //new Consumer(q);
        System.out.println("Press Control-C to stop.");
    }

}

class Q {
    int n;
    boolean valueSet = false;

    synchronized int get() throws InterruptedException {
        if (!valueSet) wait();

        System.out.println("Got: " + n);
        valueSet = false;
        notify();
        return n;
    }

    synchronized void put(int n) throws InterruptedException {
        if (valueSet) wait();

        this.n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}

class Producer implements Runnable {
    Q q;

    Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    public void run() {
        int i = 0;
        while (true) {
            try {
                q.put(i++);
                Thread.currentThread().sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    Q q;

    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        while (true) {
            try {
                q.get();
                Thread.currentThread().sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
