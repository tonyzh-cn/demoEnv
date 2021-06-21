package com.example.demo.java.concurrent.distributed.lock.mysql;

import org.springframework.stereotype.Repository;

@Repository
public interface LockMapper {
    int insert(int id);
    int deleteByPrimaryKey(int id);
}
