package com.example.demo.java.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PrintParamHandler<T> implements InvocationHandler {
	   //invocationHandler持有的被代理对象
	    T target;
	    
	    public PrintParamHandler(T target) {
	       this.target = target;
	    }
	    
	    /**
	     * proxy:代表动态代理对象
	     * method：代表正在执行的方法
	     * args：代表调用目标方法时传入的实参
	     */
	    @Override
	    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	        System.out.println("method name:"+method.getName() + " => method args:"+args);
	        return method.invoke(target, args);
	    }
	}
