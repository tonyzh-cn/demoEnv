package com.example.demo.java.concurrent.distributed.lock.zookeeper;

public interface Lock {
    void getLock();
    void unLock();
}
