package com.example.demo.java.rmi;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, NamingException {
        /**
         * 调用服务端的对象
         */
        IHello hello = (IHello) Naming.lookup("rmi://127.0.0.1:1099/hello");
        hello.say();

        /**
         * 从服务端获取字节码，然后在客户端加载并在客户端执行
         */
//        IHello hello = (IHello) new InitialContext().lookup("rmi://127.0.0.1:1099/hello");
//        hello.say();
    }
}
