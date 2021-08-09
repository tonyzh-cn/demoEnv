package com.example.demo.springDemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
       String s1="a";
       String s2="b";
       String s3=new Integer(1)+s2;
    }

    private String f(){
        Integer a = 1;
        String s = a+"abc";
        return s;
    }
}
