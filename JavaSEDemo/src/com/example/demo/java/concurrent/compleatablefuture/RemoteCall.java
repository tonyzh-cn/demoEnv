package com.example.demo.java.concurrent.compleatablefuture;

import java.util.*;

public class RemoteCall {
    public List<Map<String, String>> batchQuery(List<Map<String, String>> params) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Map<String, String> param : params) {
            Map<String,String> map = new HashMap<>();
            map.put("id",param.get("id"));
            map.put("serialNo",param.get("serialNo"));
            map.put("orderTime",new Date().toString());
            result.add(map);
        }
        return result;
    }
}
