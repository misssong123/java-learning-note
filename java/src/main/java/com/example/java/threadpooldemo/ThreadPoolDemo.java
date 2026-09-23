package com.example.java.threadpooldemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
                corePoolSize*2, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        executor.execute(()->{
            System.out.println("hello world");
        });
        executor.shutdown();
    }
}
