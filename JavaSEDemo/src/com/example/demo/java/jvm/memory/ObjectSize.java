package com.example.demo.java.jvm.memory;

import org.apache.lucene.util.RamUsageEstimator;

import java.util.ArrayList;
import java.util.List;

/**
 *  <dependency>
 *       <groupId>org.apache.lucene</groupId>
 *       <artifactId>lucene-core</artifactId>
 *       <version>4.0.0</version>
 *     </dependency>
 *
 * String objSize = RamUsageEstimator.humanSizeOf(order) ;
 */
public class ObjectSize {
    static class Order{
        private int id;
        private String name;
        private byte[] bytes = new byte[1024*1024];
    }
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        while (true){
            orders.add(new Order());
            String objSize = RamUsageEstimator.humanSizeOf(orders);
            System.out.println(objSize);
        }
    }
}
