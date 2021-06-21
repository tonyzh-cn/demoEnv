package com.example.demo.java.concurrent.reorder;

import java.util.HashSet;
import java.util.Set;

public class ReorderDemo {
    static int a =0;
    static int b =0;
    static int x=0;
    static int y=0;
    public static void main(String[] args) throws InterruptedException {
        Set<String> result = new HashSet<>();
        for(int i=0;i<10000000;i++){
             a =0;
             b =0;
             x=0;
             y=0;
            Thread t1 = new Thread(()->{
                a = y;
                x=1;
            });
            Thread t2= new Thread(()->{
               b=x;
               y=1;
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            result.add("a="+a+",b="+b);
            System.out.println(result);
        }

        System.out.println(result);
    }
}
