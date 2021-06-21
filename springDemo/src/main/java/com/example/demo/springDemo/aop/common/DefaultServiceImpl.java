package com.example.demo.springDemo.aop.common;

public class DefaultServiceImpl implements IService {
    @Override
    public String execute(String message) {
        return "[ECHO] " + message;
    }
}
