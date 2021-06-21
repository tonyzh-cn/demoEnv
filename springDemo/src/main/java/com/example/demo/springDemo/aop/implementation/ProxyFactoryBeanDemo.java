package com.example.demo.springDemo.aop.implementation;

import com.example.demo.springDemo.aop.common.IService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-ProxyFactoryBean-context.xml");

        IService echoService = context.getBean("echoServiceProxyFactoryBean", IService.class);
        echoService.execute("hello");

        context.close();
    }
}
