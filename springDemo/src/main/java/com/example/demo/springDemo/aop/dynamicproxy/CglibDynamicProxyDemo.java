package com.example.demo.springDemo.aop.dynamicproxy;

import com.example.demo.springDemo.aop.common.DefaultServiceImpl;
import com.example.demo.springDemo.aop.common.IService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDynamicProxyDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(DefaultServiceImpl.class);
        enhancer.setInterfaces(new Class[]{IService.class});
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object source, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                long start = System.currentTimeMillis();
                Object result = methodProxy.invokeSuper(source,args);
                long end = System.currentTimeMillis();

                System.out.println("[cglib] echo took " + (end-start) + "ms");
                return result;
            }
        });

        IService echoService = (IService) enhancer.create();

        System.out.println(echoService.execute("hello"));
    }
}
