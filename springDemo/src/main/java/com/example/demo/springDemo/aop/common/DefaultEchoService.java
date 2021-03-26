package com.example.demo.springDemo.aop.common;

public class DefaultEchoService implements IEchoService{
    @Override
    public String echo(String message) {
        return "[ECHO] " + message;
    }
}
