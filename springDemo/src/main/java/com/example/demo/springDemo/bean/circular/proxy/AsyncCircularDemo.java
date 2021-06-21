package com.example.demo.springDemo.bean.circular.proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AsyncCircularDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean-lifecycle-circle-proxy-context.xml");

//        A a = applicationContext.getBean(A.class);

        B b = applicationContext.getBean(B.class);

        System.out.println("I am in main.");
        b.say();
        b.say();
        b.aop();
    }
}
