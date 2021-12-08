package com.example.demo.java.concurrent.compleatablefuture;

import com.google.common.collect.Lists;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        long start = System.currentTimeMillis();
        CompletableFuture future1 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Boolean.TRUE;
        });

        CompletableFuture future2 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Boolean.TRUE;
        });

        CompletableFuture future3 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Boolean.TRUE;
        });

        CompletableFuture.allOf(future1,future2,future2).join();

        future1.get();
        future2.get();
        future3.get();

        System.out.println((System.currentTimeMillis()-start)+"ms");
    }
}
