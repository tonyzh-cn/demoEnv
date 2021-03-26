package com.example.demo.springDemo.aop.dynamicproxy;

import com.example.demo.springDemo.aop.common.DefaultEchoService;
import com.example.demo.springDemo.aop.common.IEchoService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDynamicProxyDemo {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(DefaultEchoService.class);
        enhancer.setInterfaces(new Class[]{IEchoService.class});
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

        IEchoService echoService = (IEchoService) enhancer.create();

        System.out.println(echoService.echo("hello"));
    }
}
