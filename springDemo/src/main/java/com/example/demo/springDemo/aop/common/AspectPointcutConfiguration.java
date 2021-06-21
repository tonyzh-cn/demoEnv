package com.example.demo.springDemo.aop.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class AspectPointcutConfiguration {
    @Pointcut("execution(public * *(..))")
    private void anyPublicMethod(){}

    @Before("anyPublicMethod()")
    public void doBefore(){
        System.out.println("@Before at any public method.");
    }

    @Around("anyPublicMethod()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("@Around at any public method.");
        return proceedingJoinPoint.proceed();
    }
}
