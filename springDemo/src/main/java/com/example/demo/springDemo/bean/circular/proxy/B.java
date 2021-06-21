package com.example.demo.springDemo.bean.circular.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class B {
    @Autowired
//    @Lazy
    private A a;

//    @AopAnnotation
//    @Async
//    @Transactional
    @Cacheable(value = "b",key = "b")
    public void say(){
        System.out.println("cache");
    }

//    @AopAnnotation
    public void aop(){
        System.out.println("b");
    }
}
