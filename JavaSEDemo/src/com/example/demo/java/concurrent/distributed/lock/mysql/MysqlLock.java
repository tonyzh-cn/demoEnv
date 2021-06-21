package com.example.demo.java.concurrent.distributed.lock.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MysqlLock extends AbstractLock{
    private static final int LOCK_ID = 1;

    @Autowired
    private LockMapper lockMapper;

    @Override
    protected boolean tryLock() {
        try {
            lockMapper.insert(LOCK_ID);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    protected void waitLock() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unLock() {
        lockMapper.deleteByPrimaryKey(LOCK_ID);
    }
}
