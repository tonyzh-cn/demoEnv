package com.example.demo.java.jvm;

/**
 *
 */
public class XssDemo {
    private static int count = 100000;
    public static void main(String[] args) {
        call();
    }

    private static void call() {
        if(count > 0){
            call();
        }
    }
}
