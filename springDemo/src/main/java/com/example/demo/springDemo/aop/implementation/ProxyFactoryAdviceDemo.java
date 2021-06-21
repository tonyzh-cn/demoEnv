package com.example.demo.springDemo.aop.implementation;

import com.example.demo.springDemo.aop.common.MyMethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;

import java.util.HashMap;
import java.util.Map;

public class ProxyFactoryAdviceDemo {
    public static void main(String[] args) {
        HashMap map = new HashMap<>();
        ProxyFactory proxyFactory = new ProxyFactory(map);
        proxyFactory.addAdvice(new MyMethodInterceptor());
        Map proxyMap = (Map) proxyFactory.getProxy();

        proxyMap.put("1","A");

    }
}
