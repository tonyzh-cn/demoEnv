package com.example.demo.java.concurrent.compleatablefuture;

import java.util.*;
import java.util.concurrent.*;

public class MyService {
    private LinkedBlockingQueue<Request> blockingQueue = new LinkedBlockingQueue();
    private RemoteCall remoteCall = new RemoteCall();

    public MyService() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(()->{
            int size = blockingQueue.size();
            if(size == 0){
                return;
            }
            List<Map<String,String>> params = new ArrayList<>();
            List<Request> requests = new ArrayList<>();
            for(int i = 0; i< size; i++){
                Request request = blockingQueue.poll();
                requests.add(request);
                Map<String,String> param = new HashMap<>();
                param.put("id",request.id);
                param.put("serialNo",request.serialNo);
                params.add(param);
            }

            System.out.println("batch:"+requests.size());
            List<Map<String,String>> responses = remoteCall.batchQuery(params);

            for(Request request : requests){
                String serialNo = request.serialNo;
                for(Map<String,String> response : responses){
                    if(serialNo.equals(response.get("serialNo"))){
                        request.completableFuture.complete(response);
                    }
                }
            }
        },0,10,TimeUnit.MILLISECONDS);
    }

    class Request{
        private String id;
        private String serialNo;
        private CompletableFuture<Map<String,String>> completableFuture;
    }
    public Map<String,String> query(String id) throws ExecutionException, InterruptedException {
        Request request = new Request();
        request.id = id;
        request.serialNo = UUID.randomUUID().toString();
        CompletableFuture<Map<String,String>> completableFuture = new CompletableFuture<>();
        request.completableFuture = completableFuture;
        blockingQueue.add(request);

        return completableFuture.get();
    }
}
