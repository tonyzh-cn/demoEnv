package com.example.demo.springDemo.aop.overview;

import java.net.URLClassLoader;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        System.out.println(classLoader);

        ClassLoader parentClassLoader = classLoader.getParent();
        while(true){
            if(parentClassLoader == null){
                break;
            }
            System.out.println(parentClassLoader);
            parentClassLoader = parentClassLoader.getParent();
        }

    }
}
