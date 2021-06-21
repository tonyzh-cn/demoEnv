package com.example.demo;

import java.util.List;

public class DefaultExecutor implements Executor{
    private final Configuration configuration;

    public DefaultExecutor(Configuration configuration) {
        this.configuration = configuration;
    }


    public <E> List<E> query(MappedStatement ms, Object parameter) {
        return null;
    }
}
