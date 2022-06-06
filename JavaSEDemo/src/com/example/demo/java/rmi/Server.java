package com.example.demo.java.rmi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, NamingException {
        LocateRegistry.createRegistry(1099);
        Registry registry= LocateRegistry.getRegistry();

        /**
         * 给客户端返回一个服务端对象的名称引用
         */
//        Naming.rebind("rmi://127.0.0.1:1099/hello", new HelloImpl("zhangsan"));
        registry.rebind("hello", new HelloImpl("zhangsan"));

        /**
         * 给客户端返回字节码，必须返回ObjectFactory类型的字节码
         */
//        Reference reference = new Reference("com.example.demo.java.rmi.Hello","com.example.demo.java.rmi.Factory",null);
//        ReferenceWrapper wrapper = new ReferenceWrapper(reference);
//        registry.bind("hello",wrapper);

    }
}
