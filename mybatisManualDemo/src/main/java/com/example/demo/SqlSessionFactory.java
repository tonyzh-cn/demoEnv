package com.example.demo;

public class SqlSessionFactory {
    private final Configuration configuration = new Configuration();

    public SqlSessionFactory() {
        loadDbInfo();
        loadMappersInfo();
    }

    public SqlSession openSession(){
        return new DefaultSqlSession(configuration);
    }

    private void loadMappersInfo() {

    }

    private void loadDbInfo() {
        configuration.setJdbcDriver("");

    }
}
