package com.example.demo.java.jvm.memory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * -XX:MaxDirectMemory=100M
 */
public class DirectMemoryOOM {
    public static void main(String[] args) {
        final int _1M = 1024 * 1024;
        List<ByteBuffer> byteBuffers = new ArrayList<>();

        int count=0;
        while (true){
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1M);
            byteBuffers.add(byteBuffer);
            System.out.println(++count);
        }
    }
}
