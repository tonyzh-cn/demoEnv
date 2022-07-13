package com.example.demo.aspect;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author zhangtao
 * @since 2022/6/27 9:17
 */
@Aspect
@Component
public class AspectHolder {
    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    private void pointcut(){}

    @After("pointcut()")
    public void after(){
        System.out.println("after");
    }

    @Before("pointcut()")
    public void before(){
        System.out.println("before");
    }

    @AfterReturning("pointcut()")
    public void afterReturning(){
        System.out.println("afterReturning");
    }

    @Around("pointcut()")
    public void around(){
        System.out.println("around");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        System.out.println("afterThrowing");
    }
}
