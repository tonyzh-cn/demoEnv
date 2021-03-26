package com.example.demo.springDemo.aop.common;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

public class EchoServiceMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("拦截到方法 ： " + invocation.getMethod());
        return invocation.proceed();
    }
}
