package com.example.demo.java.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test2 {
    public static void main(String[] args) throws Exception {
        doRequest();
    }

    private static void doRequest() {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://www.163.com/");
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(false);
            conn.setRequestMethod("GET"); // 设定请求方式
            conn.connect();

            InputStream in = conn.getInputStream();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                // System.out.println(line);
            }

            reader.close();

            // 判断是否正常响应数据
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("错误响应码 " + conn.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("异常！");
        } finally {
            if (conn != null) {
                conn.disconnect(); // 中断连接
            }
        }
    }

}
