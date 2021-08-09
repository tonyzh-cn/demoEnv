package com.example.minio;

import com.sun.javaws.exceptions.InvalidArgumentException;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.apache.log4j.Logger;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class FileUploader {
    private static final Logger log = Logger.getLogger(FileUploader.class);

    private static MinioClient minioClient;
    private final static String url = "http://192.168.52.59:9000/";
//    private final static String url = "http://192.168.50.22:9000/";
//    private final static String url = "http://127.0.0.1:9000/";

//    private final static String TEST_PNG_FILE ="D:\\Tmp\\test.png";
    private final static String TEST_PNG_FILE ="E:\\Tmp\\评审专家信息导入模板.xlsm";
    static {
        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        minioClient = new MinioClient(url, "minioadmin", "minioadmin");
//            minioClient = new MinioClient(url);

    }
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeyException, InsufficientDataException, InternalException, RegionConflictException, ErrorResponseException, InvalidArgumentException, InvalidBucketNameException, XmlParserException, ServerException, InvalidResponseException {
//        putObject();
//        putObject("test");
//        putObject("test2","test1.png");
//        buckssetPolicy();
//        getObject();
//        getObject("test","test1.png");
//        listObjects();
//        removeBucket();
//        forceRemoveBucket("aaa");
//        createBucket();
//        forceCreateBucket("aaa");

//        upload("D:\\programs\\mysql-5.7.23-winx64\\data");

//        upload();

//        removeObject();

//        download();

        uploadObject("csdbdx-award-attachment","评审专家信息导入模板.xlsm", TEST_PNG_FILE);
    }

    private static void download() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, InvalidBucketNameException, ErrorResponseException, XmlParserException, ServerException, InvalidResponseException {
        String bucketName = "test";
        if(!bucketExists(bucketName)){
            return;
        }
        long start = System.currentTimeMillis();
        for(int i=1;i<=100000;i++){
            if(i%10000 == 1){
                System.out.println("第"+(i/10000 + 1)+"个10000开始时间："+new Date());
            }
            try {
                InputStream is = minioClient.getObject(bucketName,bucketName+i+".png");
                writeToLocal(is,"E:\\Tmp\\download\\"+bucketName+i+".png");
            } catch(MinioException e) {
                System.out.println("Error occurred: " + e);
            }
        }
        System.out.println("结束时间："+new Date());
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void removeObject() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, InvalidBucketNameException, ErrorResponseException, XmlParserException, ServerException, InvalidResponseException {
        String bucketName = "test";
        if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())){
            return;
        }
        long start = System.currentTimeMillis();
        for(int i=1;i<=5000;i++){
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(bucketName+i+"-binlog").build());
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void upload() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, InvalidBucketNameException, ErrorResponseException, RegionConflictException, InvalidArgumentException, XmlParserException, ServerException, InvalidResponseException {
        String bucketNamePrefix = "test";
        String bucketName = "test";
        if(!bucketExists(bucketName)){
            makeBucket(bucketName);
        }

        long start = System.currentTimeMillis();
        for(int i=1;i<=100000;i++){
            if(i%5000 == 1){
                log.info("第"+(i/5000 + 1)+"个5000开始时间："+new Date());
            }
            uploadObject(bucketName,bucketNamePrefix+i+".png",TEST_PNG_FILE);
        }
        log.info("结束时间："+new Date());
        log.info(System.currentTimeMillis() - start);
    }

    private static void makeBucket(String bucketName) throws IOException, InvalidKeyException, InvalidResponseException, RegionConflictException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
    }

    private static void upload(String rootPath) {
        File root = new File(rootPath);
        if(!root.exists() || !root.isDirectory()){
            return;
        }
        long start = System.currentTimeMillis();
        String bucketName = rootPath.substring(rootPath.lastIndexOf(File.separator)+1).replace("@","-");
        File []childs =root.listFiles();
        if(childs.length == 0){
            return;
        }
        for(File child : childs){
            doUpload(bucketName,child);
        }
        System.out.println(System.currentTimeMillis()- start);
    }

    private static void doUpload(String bucketName,File currentFile){
        bucketName = bucketName.replace("@","-");
        String currentFilePath = currentFile.getPath();
        String currentFileName = currentFilePath.substring(currentFilePath.lastIndexOf(File.separator)+1).replace("@","-");
        if(currentFile.isFile()){
            try {
                if(!bucketExists(bucketName)){
                    makeBucket(bucketName);
                }
                uploadObject(bucketName,currentFileName,currentFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(currentFile.isDirectory()){
            File []childs =currentFile.listFiles();
            if(childs.length == 0){
                return;
            }
            bucketName = bucketName + "-" +currentFileName;
            for(File child : childs){
                doUpload(bucketName,child);
            }
        }
    }

    private static void bucketPolicy() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, ErrorResponseException,  InvalidBucketNameException,  InternalException, RegionConflictException, InvalidArgumentException {
//        makeBucket("test");
//        uploadObject("test","test.png",TEST_PNG_FILE);
//        minioClient.setBucketPolicy("test","test", PolicyType.READ_ONLY);
    }

    private static void forceCreateBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException,  InvalidBucketNameException,  ErrorResponseException, RegionConflictException, XmlParserException, ServerException, InvalidResponseException {
        makeBucket(bucketName);
        System.out.println(bucketName + " Bucket created.");
    }

    private static void createBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException,  InvalidBucketNameException,  ErrorResponseException, RegionConflictException, XmlParserException, ServerException, InvalidResponseException {
        boolean isExist = bucketExists(bucketName);
        if(!isExist) {
            makeBucket(bucketName);
            System.out.println(bucketName + " Bucket created.");
        }
    }

    private static boolean bucketExists(String bucketName) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    private static void createBucket() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException,  InvalidBucketNameException,  ErrorResponseException, RegionConflictException, XmlParserException, ServerException, InvalidResponseException {
        for(char start = 'a';start<='z';start++){
            String bucketName = ""+start+start+start;
            boolean isExist = bucketExists(bucketName);
            if(!isExist) {
                makeBucket(bucketName);
                System.out.println(bucketName + " Bucket created.");
            }
        }
    }

    private static void removeBucket() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException,  InvalidBucketNameException,  ErrorResponseException, RegionConflictException, InvalidArgumentException, XmlParserException, ServerException, InvalidResponseException {
        // 使用putObject上传一个文件到存储桶中。
        for(char start = 'a';start<='z';start++){
            String bucketName = ""+start+start+start;
            removeBucket(bucketName);
        }
    }

    private static void forceRemoveBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException,  InvalidBucketNameException,  ErrorResponseException, RegionConflictException, InvalidArgumentException, XmlParserException, ServerException, InvalidResponseException {
        minioClient.removeBucket(bucketName);
        System.out.println("Bucket removed.");
    }

    private static void removeBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException,  InvalidBucketNameException,  ErrorResponseException, RegionConflictException, InvalidArgumentException, XmlParserException, ServerException, InvalidResponseException {
        boolean isExist = bucketExists(bucketName);
        if(isExist) {
            Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                minioClient.removeObject(bucketName,item.objectName());
            }
            minioClient.removeBucket(bucketName);
            System.out.println("Bucket removed.");
        }
    }

    private static void getObject(String bucketName,String objectName) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            long start = System.currentTimeMillis();
            minioClient.getObject(bucketName,objectName);
            System.out.println(System.currentTimeMillis() - start);
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    private static void getObject() throws  NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            InputStream is = minioClient.getObject(GetObjectArgs.builder().bucket("hljzyydx-item-attachment").object("2020110219122182579.pdf").build());
            writeToLocal(is,"E:\\Tmp\\copy.jpg");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    private static void writeToLocal(InputStream is,String tmpPath) throws IOException {
        File target = new File(tmpPath);
        target.createNewFile();
        OutputStream os = new FileOutputStream(target);
//        byte []bytes = new byte[1024];
//        int bytesRead = 0;
//        while((bytesRead = is.read(bytes)) > 0){
//            os.write(bytes,0,bytesRead);
//        }
//
//        is.close();
//        os.flush();
//        os.close();

        write(is,os);
    }

    private static void write(InputStream fis, OutputStream os ) throws IOException {
        try {
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1){
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null){
                os.close();
            }
            if (fis != null){
                fis.close();
            }
        }
    }

    private static void listObjects() throws  NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("test");
            if(isExist) {
                // 列出'my-bucketname'里的对象
                Iterable<Result<Item>> myObjects = minioClient.listObjects("test","test");
                for (Result<Item> result : myObjects) {
                    Item item = result.get();
                    System.out.println(item.lastModified() + ", " + item.size() + ", " + item.objectName());
                }
            }
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }

    }

    private static void putObject(String bucketName) throws  NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            for(char start = 'a';start<='c';start++){
                String objectName = ""+start+start+start+".png";
                boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
                if(isExist) {
                    System.out.println("Bucket already exists.");
                } else {
                    // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                    makeBucket(bucketName);
                }
                uploadObject(bucketName,objectName, TEST_PNG_FILE);
            }
            System.out.println("E:\\Tmp\\test.jpg is successfully uploaded as test.jpg to `test` bucket.");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    private static void uploadObject(String bucketName, String objectName, String filePath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, InvalidBucketNameException, ErrorResponseException {
        minioClient.uploadObject(UploadObjectArgs.builder().bucket(bucketName).object(objectName).filename(filePath).build());
    }

    private static void putObject() throws  NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            for(char start = 'a';start<='z';start++){
                String bucketName = ""+start+start+start;
                boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
                if(isExist) {
                    System.out.println("Bucket already exists.");
                } else {
                    // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                    makeBucket(bucketName);
                }
                uploadObject(bucketName,bucketName+".png", TEST_PNG_FILE);
            }
            System.out.println("E:\\Tmp\\test.jpg is successfully uploaded as test.jpg to `test` bucket.");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    private static void putObject(String bucketName,String objectName) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                makeBucket(bucketName);
            }
            long start =System.currentTimeMillis();
            uploadObject(bucketName,objectName, "E:\\Tmp\\2020102113352173404.pdf");
            System.out.println(System.currentTimeMillis() - start);
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}
