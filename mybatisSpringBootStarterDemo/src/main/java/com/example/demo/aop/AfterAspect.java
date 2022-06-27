package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterAspect {
    @Pointcut("execution(public * methodA(..))")
    public void pointcut(){}

    @Around(value = "pointcut()")
    public void doAround (JoinPoint joinpoint){
        System.out.println("doAround.");
    }

    @Before(value = "pointcut()")
    public void doBefore (JoinPoint joinpoint){
        System.out.println("doBefore.");
    }

    @After(value = "pointcut()")
    public void doAfter (JoinPoint joinpoint){
        System.out.println("doAfter.");
    }

    @AfterReturning(value = "pointcut()")
    public void doAfterReturning (JoinPoint joinpoint){
        System.out.println("doAfterReturning.");
    }

    @AfterThrowing(value = "pointcut()")
    public void doAfterThrowing (JoinPoint joinpoint){
        System.out.println("AfterThrowing.");
    }
}
