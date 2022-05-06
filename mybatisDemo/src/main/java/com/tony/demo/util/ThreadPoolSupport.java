package com.tony.demo.util;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolSupport {

    private static ExecutorService  executorService = null;

    private static Map<String,ExecutorService>  executors = new ConcurrentHashMap<>();

    private static Lock lock = new ReentrantLock();

    public static ExecutorService getExecutor(String prefix){
        ExecutorService executor = null;
        try{
            lock.lock();
            executor = executors.get(prefix);
            if(executor == null){
                executor = Executors.newFixedThreadPool(50,new CustomizableThreadFactory(prefix));
                executors.put(prefix,executor);
            }
        }finally {
            lock.unlock();
            return executor;
        }
    }

    private static ExecutorService getExecutor()
    {
        if (executorService == null)
        {
            synchronized(ThreadPoolSupport.class)
            {
                if(executorService == null)
                {
                    executorService = Executors.newFixedThreadPool(10);
                }
            }
        }
        return executorService;
    }

    public static synchronized void execute(Runnable runnable){
        getExecutor().execute(runnable);
    }

    public static synchronized void close(){
        if(executorService == null){
            return;
        }
        executorService.shutdown();
        while (true) {
            if(executorService.isTerminated()){
                executorService = null;
                break;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                //Ignore
            }
        }
    }
}
