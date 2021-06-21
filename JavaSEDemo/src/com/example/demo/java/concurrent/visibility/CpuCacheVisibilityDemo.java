package com.example.demo.java.concurrent.visibility;

public class CpuCacheVisibilityDemo {
    private long count = 0;
    private void add10K() {
        System.out.println("init="+count);
        int idx = 0;
        while(idx++ < 100000000) {
            count += 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final CpuCacheVisibilityDemo test = new CpuCacheVisibilityDemo();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(()->{
            test.add10K();
        });
        Thread th2 = new Thread(()->{
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        System.out.println(test.count);
    }
}