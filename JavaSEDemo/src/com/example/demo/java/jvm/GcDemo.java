package com.example.demo.java.jvm;

/**
 * -verbose:gc -Xmx30m -Xms30m -Xmn9m -XX:SurvivorRatio=1
 * Eden:3m
 * from:3m
 * to:3m
 * old:21m
 */
public class GcDemo {
    public static void main(String[] args) throws InterruptedException {
        byte [] b1 = new byte[2*1024*1024];
        Thread.sleep(20000);

        allocate();
        Thread.sleep(4000);
        allocate();
        Thread.sleep(4000);
        allocate();
        Thread.sleep(4000);
        allocate();
        Thread.sleep(4000);
        allocate();
        Thread.sleep(4000);
        allocate();
        Thread.sleep(4000);
        allocate();
        Thread.sleep(4000);
        allocate();
    }

    private static void allocate() throws InterruptedException {
        byte [] b1 = new byte[2*1024*1024];
    }
}
