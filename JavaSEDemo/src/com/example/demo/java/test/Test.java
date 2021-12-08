package com.example.demo.java.test;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader.getSystemClassLoader().loadClass("com.example.demo.java.test.A");
//        Class.forName("com.example.demo.java.test.A");
    }
}
