package com.example.demo.java.concurrent.collection;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.put("a");
        queue.put("b");
        queue.poll();
    }
}
