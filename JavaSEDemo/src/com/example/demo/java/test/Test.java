package com.example.demo.java.test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class Test {
    private int a = 9;
    public static void main(String[] args ) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Pattern p = Pattern.compile("/index.shtml|/clearAllCache.shtml|/static/*|.jsp$");
        System.out.println(p.matcher("http://xxxx/xxx.jspdddd").find());
    }
}
