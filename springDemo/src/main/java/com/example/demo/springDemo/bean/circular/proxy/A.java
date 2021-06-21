package com.example.demo.springDemo.bean.circular.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class A {
    @Autowired
//    @Lazy
    private B b;

//    @AopAnnotation
//    @Async
//    @Transactional
    public void say(){
    }

}
