package com.example.java.countDownLatchvscyclicBarrier;

import java.util.Random;
import java.util.concurrent.*;

public class CountDownLatchVsCyclicBarrierDemo {
    public static void main(String[] args) {
    }
    public static void countDownLatchDemo(){
        int corePoolSize = Runtime.getRuntime().availableProcessors() ;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,corePoolSize,10, TimeUnit.SECONDS,new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(()->{
                try {
                    Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                    }
                latch.countDown();
                }
                );
            }
        try {
            latch.await();
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
        System.out.println("All tasks completed");
        executor.shutdown();
    }
    public static void cyclicBarrierDemo(){
        int corePoolSize = Runtime.getRuntime().availableProcessors() ;
        System.out.println("corePoolSize:"+corePoolSize);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,corePoolSize,10, TimeUnit.SECONDS,new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Random random = new Random(321);
        CyclicBarrier barrier = new CyclicBarrier(corePoolSize);
        for (int k = 0 ; k < 3 ; k++){
            long start = System.currentTimeMillis();
            for (int i = 0; i < corePoolSize; i++) {
                executor.execute(()->{
                            try {
                                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                                barrier.await();
                            } catch (InterruptedException | BrokenBarrierException e) {
                                e.printStackTrace();
                            }
                        }
                );
            }
            System.out.println("k:"+k+",cost:"+(System.currentTimeMillis()-start));
        }
        System.out.println("end");
        executor.shutdown();
    }
}
