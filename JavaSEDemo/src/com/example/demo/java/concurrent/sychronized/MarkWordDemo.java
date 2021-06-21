package com.example.demo.java.concurrent.sychronized;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * -XX:BiasedLockingStartupDelay=0
 * -XX:+PrintFlagsInitial
 */
public class MarkWordDemo {
    static MarkWordDemo obj = new MarkWordDemo();

    public static void main(String[] args) throws InterruptedException {
        /**
         * 新建对象后直接synchronized加锁-偏向锁
         */
//        MarkWordDemo obj1 = new MarkWordDemo();
//        synchronized (obj1){
//            System.out.println(ClassLayout.parseInstance(obj1).toPrintable(obj1));//101
//        }


        /**
         *  新建的对象-无锁可偏向
         *  计算hashcode-无锁不可偏向
         *  synchronized加锁-轻量级锁
         */
//        MarkWordDemo obj2 = new MarkWordDemo();
//        System.out.println(ClassLayout.parseInstance(obj2).toPrintable(obj2));//101
//        obj2.hashCode();
//        System.out.println(ClassLayout.parseInstance(obj2).toPrintable(obj2));//001
//        synchronized (obj2){
//            System.out.println(ClassLayout.parseInstance(obj2).toPrintable(obj2));//00
//        }



        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            lock();
        });

        Thread.sleep(1000);
        executorService.submit(()->{
            lock();
        });
    }

    private static void lock(){
        synchronized (obj){
            System.out.println(ClassLayout.parseInstance(obj).toPrintable(obj));
        }
    }
}
