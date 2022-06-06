package com.example.demo.java.rmi;

import java.rmi.Remote;

public interface IHello extends Remote {
    String say() throws java.rmi.RemoteException;
}
