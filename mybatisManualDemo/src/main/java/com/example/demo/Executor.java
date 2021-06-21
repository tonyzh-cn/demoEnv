package com.example.demo;

import java.util.List;

public interface Executor {
    <E> List<E> query(MappedStatement ms, Object parameter);
}
