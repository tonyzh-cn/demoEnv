package com.example.demo.springDemo.bean.circular.proxy;

import org.aopalliance.aop.Advice;
import org.springframework.aop.*;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//@Component
public class MyAdvisor extends StaticMethodMatcherPointcutAdvisor {
//    @Autowired
//    private A a;

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return false;
    }

}
