package com.example.demo.java.concurrent.distributed.lock.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LockController {
    @Resource(name = "mysqlLock")
    private MysqlLock lock;

    @RequestMapping
    public void add(){
        lock.getLock();
        // access public resource

        lock.unLock();
    }
}
