package com.example.demo.java.concurrent.distributed.lock.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

public class ZookeeperTest {
    private final static String ZOOKEEPER_URL = "localhost:2181";
    private static ZkClient zkClient = new ZkClient(ZOOKEEPER_URL,1000,1000,new SerializableSerializer());
    public static void main(String[] args) throws InterruptedException {
        String path = "/watcher";
        zkClient.createPersistent(path);
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                System.out.println("Data changed:"+path);
            }

            @Override
            public void handleDataDeleted(String path) throws Exception {
                System.out.println("Data deleted:"+path);
            }
        };

        zkClient.subscribeDataChanges(path,listener);

        Thread.currentThread().join();
    }
}
