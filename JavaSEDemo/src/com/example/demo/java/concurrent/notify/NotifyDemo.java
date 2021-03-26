package com.example.demo.java.concurrent.notify;

public class NotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        Allocator allocator = new Allocator();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                allocator.apply(a,b);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                allocator.free(a,b);
            }
        });
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                allocator.apply(a,b);
                System.out.println(Thread.currentThread().getName());
                allocator.free(a,b);
            }
        });
        t2.start();

        Thread.sleep(1000);

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                allocator.apply(a,b);
                System.out.println(Thread.currentThread().getName());
                allocator.free(a,b);
            }
        });
        t3.start();
    }
}
