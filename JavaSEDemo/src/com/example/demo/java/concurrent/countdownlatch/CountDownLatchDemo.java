package com.example.demo.java.concurrent.countdownlatch;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo implements Runnable{
    private static CountDownLatch countDownLatch = new CountDownLatch(50);

    private static List<Integer> result = new Vector<>();

    private int count;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();

        for(int i=0;i<50;i++){
            new Thread(countDownLatchDemo).start();
            countDownLatch.countDown();
        }

        Thread.sleep(1000);

        Collections.sort(result);

        for(Integer i : result){
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
            result.add(count++);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
