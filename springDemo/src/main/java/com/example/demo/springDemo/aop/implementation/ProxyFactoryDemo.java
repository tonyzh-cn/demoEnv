package com.example.demo.springDemo.aop.implementation;

import com.example.demo.springDemo.aop.common.DefaultEchoService;
import com.example.demo.springDemo.aop.common.EchoServiceMethodInterceptor;
import com.example.demo.springDemo.aop.common.IEchoService;
import org.springframework.aop.framework.ProxyFactory;

import java.util.HashMap;
import java.util.Map;

public class ProxyFactoryDemo {
    public static void main(String[] args) {
        HashMap map = new HashMap<>();
        ProxyFactory proxyFactory = new ProxyFactory(map);
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        Map echoService = (Map) proxyFactory.getProxy();

        echoService.put("1","A");

    }
}
