package com.example.demo.springDemo.aop.dynamicproxy;

import java.lang.reflect.Proxy;
import java.util.Comparator;

public class JdkDynamicProxyDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Object proxy = Proxy.newProxyInstance(classLoader,new Class[]{Comparator.class},((proxy2, method, args1) -> {
            return null;
        }));

        System.out.println(proxy);
    }
}
