package com.example.demo;

public class MybatisManualDemo {
    public static void main(String[] args) {
        SqlSessionFactory factory = new SqlSessionFactory();
        SqlSession session = factory.openSession();

        UserMapper userMapper = session.getMapper(UserMapper.class);


    }
}
