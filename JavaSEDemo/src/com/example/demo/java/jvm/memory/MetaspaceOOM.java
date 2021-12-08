package com.example.demo.java.jvm.memory;

import com.example.demo.java.jvm.GcDemo;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 元空间默认使用机器的内存，可通过-XX:MetaspaceSize=20M -XX:MaxMetaspaceSize=20M设置
 */
public class MetaspaceOOM {
    public static void main(String[] args) throws InterruptedException {
        long count=0;
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(GcDemo.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,objects);
                }
            });

            enhancer.create();

//            Thread.sleep(10);
            System.out.println(++count+"created.");
        }
    }
}
