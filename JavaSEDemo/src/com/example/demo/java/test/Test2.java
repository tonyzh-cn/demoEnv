package com.example.demo.java.test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {
    public static void main(String[] args ) {
        Random random = new Random();
        for(int i=0;i<100;i++){
            System.out.println(random.nextInt(10));
        }
    }



    private String match(String content,String reg){
        String fieldValue="";
        Pattern pattern = Pattern.compile(reg);// ƥ���ģʽ
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            int i = 1;
            fieldValue=matcher.group(i);
            i++;
        }
        return fieldValue;
    }

}
