package com.example.demo.java.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 如果要在服务端创建对象，必须继承UnicastRemoteObject；
 * 如果服务端只给客户端返回字节码，可以不继承UnicastRemoteObject
 */
public class HelloImpl extends UnicastRemoteObject implements IHello, Serializable {
    private String prefix;

    static {
        System.out.println("hello");
    }

    public HelloImpl() throws RemoteException {
        super();
    }

    public HelloImpl(String prefix) throws RemoteException {
        super();
        this.prefix=prefix;
    }

    @Override
    public String say() {
        System.out.println(prefix+"hello");

        return prefix+"hello";
    }
}
