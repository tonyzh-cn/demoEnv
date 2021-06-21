package com.example.demo.springDemo.aop.implementation;

import com.example.demo.springDemo.aop.common.AspectPointcutConfiguration;
import com.example.demo.springDemo.aop.common.DefaultServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@Aspect
@Configuration
@EnableAspectJAutoProxy
public class AspectJAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AspectJAnnotationDemo.class);
        applicationContext.register(AspectPointcutConfiguration.class);

        applicationContext.refresh();
        applicationContext.close();
    }

    @Bean
    public DefaultServiceImpl echoService(){
        return new DefaultServiceImpl();
    }
}
