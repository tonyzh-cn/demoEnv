package com.example.demo.java.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(()->{
            try{
                lock.lock();
                LockSupport.park();
            }finally {
                lock.unlock();
            }
        });
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(()->{
            LockSupport.unpark(t1);
            try{
                lock.lock();
                System.out.println("i am t2.");
            }finally {
                lock.unlock();
            }
        });
        t2.start();
    }
}
