package com.example.demo;

import java.lang.reflect.Proxy;
import java.util.List;

public class DefaultSqlSession implements SqlSession{
    private final Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new DefaultExecutor(configuration);
    }

    public <T> T selectOne(String statement, Object parameter) {
        List<T> list = this.<T>selectList(statement, parameter);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new RuntimeException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }

    public <E> List<E> selectList(String statement, Object parameter) {
        MappedStatement ms = configuration.getMappedStatements().get(statement);
        return executor.query(ms,parameter);
    }

    public <T> T getMapper(Class<T> type) {
        MapperProxy proxy = new MapperProxy(this);
        return (T)Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},proxy);
    }
}
