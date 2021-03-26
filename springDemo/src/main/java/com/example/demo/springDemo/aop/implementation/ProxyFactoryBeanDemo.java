package com.example.demo.springDemo.aop.implementation;

import com.example.demo.springDemo.aop.common.IEchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-aop-context.xml");

        IEchoService echoService = context.getBean("echoServiceProxyFactoryBean",IEchoService.class);

        System.out.println(echoService.echo("hello"));

        context.close();
    }
}
