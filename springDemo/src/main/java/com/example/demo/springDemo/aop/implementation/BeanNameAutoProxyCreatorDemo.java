package com.example.demo.springDemo.aop.implementation;

import com.example.demo.springDemo.aop.common.IService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanNameAutoProxyCreatorDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-BeanNameAutoProxyCreator-context.xml");
        IService echoService = context.getBean("echoService", IService.class);
        echoService.execute("hello");
        context.close();
    }
}
