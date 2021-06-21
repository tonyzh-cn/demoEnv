package com.example.demo.java.concurrent.distributed.lock.mysql;

public interface Lock {
    void getLock();
    void unLock();
}
