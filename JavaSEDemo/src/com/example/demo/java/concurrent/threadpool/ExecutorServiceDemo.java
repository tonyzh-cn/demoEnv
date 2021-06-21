package com.example.demo.java.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });
        executorService.shutdown();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
            }
        });
    }
}
