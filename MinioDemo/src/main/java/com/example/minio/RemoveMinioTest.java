package com.example.minio;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 删除minio中所有的bucket
 * @author zhangtao
 * @since 2022/7/7 14:34
 */
public class RemoveMinioTest {
    private static MinioClient minioClient;
    //minio服务器地址
    private final static String endpoint = "http://192.168.50.254:9000/";
    //用户名
    private final static String accessKey="admin";
    //密码
    private final static String secretKey="pms123456";

    static {
        minioClient=MinioClient.builder().endpoint(endpoint).credentials(accessKey,secretKey).build();
    }

    public static void main(String[] args) throws Exception {
        long start=System.currentTimeMillis();
        List<Bucket> buckets = minioClient.listBuckets();

        for(Bucket bucket : buckets){
            removeObjects(bucket.name(),objectNames(bucket.name()));
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucket.name()).build());
        }

        System.out.println("took "+(System.currentTimeMillis()-start));
    }

    private static List<String> objectNames(String bucketName) throws Exception {
        List<String> objectNames = new ArrayList<>();
        Iterator<Result<Item>> myObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build()).iterator();
        while(myObjects.hasNext()){
            Result<Item> result=myObjects.next();
            Item item = result.get();
            objectNames.add(item.objectName());
        }

        return objectNames;
    }

    private static boolean removeObjects(String bucketName, List<String> objectNames) throws Exception {
        List<DeleteObject> deleteObjects = new ArrayList<>(objectNames.size());
        for (String objectName : objectNames) {
            deleteObjects.add(new DeleteObject(objectName));
        }
        Iterable<Result<DeleteError>> results = minioClient.removeObjects(
                RemoveObjectsArgs.builder()
                        .bucket(bucketName)
                        .objects(deleteObjects)
                        .build()
        );
        for (Result<DeleteError> result : results) {
            DeleteError error = result.get();
            System.out.println("Error in deleting object " + error.objectName() + "; " + error.message());
        }
        return true;
    }
}
