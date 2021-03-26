package com.example.demo.springDemo.bean.circular.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OperateRecordHistoryService {
    @Autowired
    private EPersonService studentService;

    @Async
    public void say(){
        for(int i=0;i<5;i++){
            System.out.println("I am in service -> "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
