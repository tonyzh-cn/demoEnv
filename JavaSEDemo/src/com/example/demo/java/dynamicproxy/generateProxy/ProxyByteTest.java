package com.example.demo.java.dynamicproxy.generateProxy;

import com.example.demo.java.dynamicproxy.Person;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;

public class ProxyByteTest {
    public static void main(String[] args) throws IOException {
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                "MyProxy", new Class[]{Person.class}, Modifier.PUBLIC | Modifier.FINAL);

        FileOutputStream fos = new FileOutputStream("D:\\Tmp\\MyProxy.class");
        fos.write(proxyClassFile);
        fos.flush();
    }
}
