package com.example.demo.java.rmi;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class Factory implements ObjectFactory {
    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        System.out.println("com.example.demo.java.rmi.Factory.getObjectInstance...");
        return new HelloImpl("zhangsan");
    }
}
