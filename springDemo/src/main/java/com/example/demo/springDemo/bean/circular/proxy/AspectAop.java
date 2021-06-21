package com.example.demo.springDemo.bean.circular.proxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectAop {
    @Pointcut("@annotation(com.example.demo.springDemo.bean.circular.proxy.AopAnnotation)")
    private void point() {};

    @Before(value = "point()")
    public void doBefore (JoinPoint joinpoint) throws Exception {
        System.out.println("aop");
    }
}
