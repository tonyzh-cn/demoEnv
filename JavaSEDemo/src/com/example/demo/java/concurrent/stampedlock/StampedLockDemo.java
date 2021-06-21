package com.example.demo.java.concurrent.stampedlock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    public static void main(String[] args) throws InterruptedException {
        StampedLock lock = new StampedLock();
        lock.tryOptimisticRead();
    }
}
