package com.example.demo.java.concurrent.distributed.lock.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

public class ZookeeperLock extends AbstractLock {
    private final static String URL = "127.0.0.1:2181";
    private final static String PATH = "/lock";
    private final static String PATH2 = "/lock2";

    private ZkClient zkClient = new ZkClient(URL);

    private CountDownLatch countDownLatch = null;
    @Override
    protected boolean tryLock() {
        try {
            zkClient.createEphemeral(PATH);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    protected void waitLock() {
        IZkDataListener listener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {}

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if(countDownLatch != null){
                    countDownLatch.countDown();
                }
            }
        };

        zkClient.subscribeDataChanges(PATH,listener);
        if(zkClient.exists(PATH)){
            countDownLatch = new CountDownLatch(1);
            try{
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        zkClient.unsubscribeDataChanges(PATH,listener);
    }

    @Override
    public void unLock() {
        if(zkClient != null){
            zkClient.delete(PATH);
        }
    }
}
