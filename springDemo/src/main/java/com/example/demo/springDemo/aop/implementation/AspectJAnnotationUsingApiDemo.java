package com.example.demo.springDemo.aop.implementation;

import com.example.demo.springDemo.aop.common.AspectConfiguration;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AspectJAnnotationUsingApiDemo {
    public static void main(String[] args) {
        //被代理对象
        Map<String,Object> cache = new HashMap<>();

        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
        proxyFactory.addAspect(AspectConfiguration.class);

        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                if("put".equals(method.getName()) && args.length == 2){
                    System.out.printf("put => key : %s, value : %s\n",args[0],args[1]);
                }

                if("get".equals(method.getName()) && args.length == 1){
                    System.out.printf("get => key : %s\n",args[0]);
                }
            }
        });

        //生成代理对象
        Map<String,Object> proxy = proxyFactory.getProxy();
        proxy.put("1","A");
        System.out.printf("proxy => %s\n",proxy.get("1"));
    }
}
