package com.example.demo.java.test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {
    public static void main(String[] args ) {
        System.out.println("1,2，3".replaceAll(",|，",";"));
    }


    private static String resolveSolrField(String fieldName) {
        fieldName = fieldName.replace("__",".").replace("_",".");
        List<String> splittedField = new ArrayList<>(Arrays.asList(fieldName.split("\\.")));
        List<String> finalField = splittedField.subList(1,splittedField.size()-1);

        return String.join(".",finalField);
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
