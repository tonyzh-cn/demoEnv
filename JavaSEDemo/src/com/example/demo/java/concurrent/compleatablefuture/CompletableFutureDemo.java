package com.example.demo.java.concurrent.compleatablefuture;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        MyService myService = new MyService();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for(int i=0;i<1000;i++){
            new Thread(()->{
                try {
                    countDownLatch.countDown();
                    countDownLatch.await();
                    myService.query("xn");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }).start();
        }

    }
}
