package com.example.demo.springDemo.bean.circular.proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AsyncCircularDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean-lifecycle-circle-proxy-context.xml");

        OperateRecordHistoryService teacherService = applicationContext.getBean(OperateRecordHistoryService.class);
        teacherService.say();

        System.out.println("I am in main.");
    }
}
