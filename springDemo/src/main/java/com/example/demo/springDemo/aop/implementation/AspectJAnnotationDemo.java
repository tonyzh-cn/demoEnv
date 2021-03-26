package com.example.demo.springDemo.aop.implementation;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Configuration
@EnableAspectJAutoProxy
public class AspectJAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AspectJAnnotationDemo.class);
        applicationContext.refresh();

        AspectJAnnotationDemo demo = applicationContext.getBean(AspectJAnnotationDemo.class);
        System.out.println(demo);

        applicationContext.close();
    }
}
