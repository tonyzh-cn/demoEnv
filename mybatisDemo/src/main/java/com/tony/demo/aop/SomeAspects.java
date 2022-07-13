package com.tony.demo.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SomeAspects {
    @Pointcut("execution(* com.tony.demo.service..*(..))")
    private void pointcut(){}

    @After("pointcut()")
    public void after(){
        throw new RuntimeException();
    }
}
