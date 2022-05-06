package com.tony.demo.util;

import com.tony.demo.util.ThreadPoolSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * @author zhangtao
 * @since 2022/3/7 18:14
 */
public class AsyncSupport {
    private final static Logger log = LoggerFactory.getLogger(AsyncSupport.class);
    List<CompletableFuture> futures = new ArrayList<>();

    private String poolName;
    private long start;

    public AsyncSupport(String poolName){
        this.poolName = poolName;
        this.start = System.currentTimeMillis();
    }

    public static synchronized AsyncSupport begin(String poolName){
        return new AsyncSupport(poolName);
    }

    public AsyncSupport addTask(Supplier supplier){
        futures.add(CompletableFuture.supplyAsync(supplier, ThreadPoolSupport.getExecutor(this.poolName)).exceptionally(e->{
            log.error("",e);
            ((Throwable)e).printStackTrace();
            return e;
        }));
        return this;
    }

    public List complete() throws Exception {
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

        List result = new ArrayList();

        for(CompletableFuture future : futures){
            Object r = future.get();
            if(r instanceof Throwable){
                throw new AsyncException((Throwable) r);
            }

            result.add(r);
        }

        System.out.println("Async took "+(System.currentTimeMillis()-start)+"ms");

        return result;
    }
}
