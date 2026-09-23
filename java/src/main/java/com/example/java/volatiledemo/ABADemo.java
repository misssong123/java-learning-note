package com.example.java.volatiledemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ABA问题的展示
 */
public class ABADemo {
    public static void main(String[] args) {
        AtomicInteger demo=new AtomicInteger(5);
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"修改demo的值结果为:"+demo.compareAndSet(5,2019));
        },"A").start();
        new Thread(()->{
            demo.compareAndSet(5,2019);
            System.out.println(Thread.currentThread().getName()+"将demo的值修改为:"+demo.get());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.compareAndSet(2019,5);
            System.out.println(Thread.currentThread().getName()+"将demo的值修改为:"+demo.get());
        },"B").start();
    }
}
/**
 * 运行结果
 * B将demo的值修改为:2019
 * B将demo的值修改为:5
 * A修改demo的值结果为:true
 */
