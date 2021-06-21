package com.example.demo.springDemo.aop.implementation;

import com.example.demo.springDemo.aop.common.AspectPointcutConfiguration;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AspectJProxyFactoryAspectDemo {
    public static void main(String[] args) {
        //被代理对象
        Map<String,Object> cache = new HashMap<>();

        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
        proxyFactory.addAspect(AspectPointcutConfiguration.class);

        //生成代理对象
        Map<String,Object> proxy = proxyFactory.getProxy();
        proxy.put("1","A");
        System.out.printf("proxy => %s\n",proxy.get("1"));
    }
}
