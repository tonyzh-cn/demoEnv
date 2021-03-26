package com.example.demo.java.dynamicproxy;

public class MonitorUtil {
    
    private static ThreadLocal<Long> tl = new ThreadLocal<>();
    
    public static void start() {
        tl.set(System.currentTimeMillis());
    }
    
    //结束时打印耗时
    public static void finish(String methodName) {
        long finishTime = System.currentTimeMillis();
        System.out.println(methodName + " took " + (finishTime - tl.get()) + "ms");
    }
}
