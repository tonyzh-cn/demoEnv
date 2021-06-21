package com.example.demo.java.concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    private AtomicInteger atomicInteger = new AtomicInteger(1);
    private long count;
    public void add(){
//        count = atomicInteger.getAndIncrement();
        count = count + 1;
//        count++;
    }
    public static void main(String[] args) throws InterruptedException {
        AtomicDemo atomicDemo = new AtomicDemo();

        List<Thread> threads = new ArrayList<>();
        for(int i=0;i<1000;i++){
            Thread t = new Thread(() -> {
                atomicDemo.add();
            });
            threads.add(t);
        }
        for(Thread t : threads){
            t.start();
        }
        for(Thread t : threads){
            t.join();
        }

        System.out.println(atomicDemo.count);
    }
}
