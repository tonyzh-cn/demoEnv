package com.tony.demo.util;

public class AsyncException extends Exception{
    public AsyncException(Throwable throwable){
        super(throwable);
    }

    public AsyncException(String message, Exception e) {
        super(message, e);
    }

    public AsyncException(String message) {
        super(message);
    }
}
