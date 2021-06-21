package com.example.demo.springDemo.aop.pointcut;

import com.example.demo.springDemo.aop.common.MyMethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.util.HashMap;
import java.util.Map;

public class StaticMethodMatcherPointcutDemo {
    public static void main(String[] args) {
        MyPointcut pointcut = new MyPointcut("put", HashMap.class);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,new MyMethodInterceptor());

        HashMap map = new HashMap<>();
        ProxyFactory proxyFactory = new ProxyFactory(map);
        proxyFactory.addAdvisor(advisor);

        Map<String,Object> proxyMap = (Map<String, Object>) proxyFactory.getProxy();
        proxyMap.put("1",1);


    }
}
