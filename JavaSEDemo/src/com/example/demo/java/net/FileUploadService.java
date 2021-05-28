package com.example.demo.java.net;

//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;

import org.apache.commons.lang3.StringUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

//@Service
public class FileUploadService {
    //远程服务地址
//    @Value("")
    private String SERVICE_URL = "http://127.0.0.1:8219/csqhdx-h/uploadFile.shtml";

    // boundary就是request头和上传文件内容的分隔符
    private final String BOUNDARY = "---------------------------123821742118716";

    public String upload(FileRequestEntity entity) throws IOException {
        String res = "";
        HttpURLConnection conn = newConnection();

        try {
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(resolveTypeBuffer(entity.getType()));

            for(Map.Entry<String, InputStream> entry : entity.getFiles().entrySet()){
                //没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
                out.write(resolveContentBuffer(entry.getKey()));

                DataInputStream in = new DataInputStream(entry.getValue());
                int bytes = 0;
                byte[] bufferOut = new byte[1024];
                while ((bytes = in.read(bufferOut)) != -1) {
                    out.write(bufferOut, 0, bytes);
                }
                in.close();
            }

            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 读取返回数据
            StringBuffer resultBuf = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                resultBuf.append(line).append("\n");
            }
            res = resultBuf.toString();
            reader.close();
            reader = null;
        } catch (Exception e) {
            System.out.println("发送POST请求出错。" + SERVICE_URL);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return res;
    }

    private byte[] resolveContentBuffer(String fileName) {
        String contentType = resolveContentType(fileName);
        StringBuffer contentBuffer = new StringBuffer();
        contentBuffer.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
        contentBuffer.append("Content-Disposition: form-data; name=\""+fileName+"\"; filename=\"" + fileName + "\"\r\n");
        contentBuffer.append("Content-Type:" + contentType + "\r\n\r\n");
        return contentBuffer.toString().getBytes();
    }

    private byte[] resolveTypeBuffer(String type) {
        StringBuffer typeBuf = new StringBuffer();
        typeBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
        typeBuf.append("Content-Disposition: form-data; name=\"type\"\r\n\r\n");
        typeBuf.append(type);
        return typeBuf.toString().getBytes();
    }

    private HttpURLConnection newConnection() throws IOException {
        URL url = new URL(SERVICE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(30000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);
        return conn;
    }

    private String resolveContentType(String fileName) {
        String contentType = new MimetypesFileTypeMap().getContentType(fileName);
        if(StringUtils.isEmpty(contentType)){
           contentType = "application/octet-stream";
        }
        else if (fileName.endsWith(".png")) {
            contentType = "image/png";
        }
        else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".jpe")) {
            contentType = "image/jpeg";
        }
        else if (fileName.endsWith(".gif")) {
            contentType = "image/gif";
        }
        else if (fileName.endsWith(".ico")) {
            contentType = "image/image/x-icon";
        }
        return contentType;
    }

    public static void main(String[] args) throws IOException {
        String result = new FileUploadService().upload(FileRequestEntity.builder().type("2")
                .addFile("test.png",new FileInputStream(new File("E:\\Tmp\\test.png")))
                .addFile("f.sql",new FileInputStream(new File("E:\\Tmp\\f.sql"))).build());

        System.out.println(result);
    }
}
