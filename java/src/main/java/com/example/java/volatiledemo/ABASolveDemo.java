package com.example.java.volatiledemo;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题的解决
 * 增加版本号
 */
public class ABASolveDemo {
    public static void main(String[] args) {
        AtomicStampedReference<Integer>demo=new AtomicStampedReference<>(5,1);//初始值，初始版本号
        new Thread(()->{
            //获得初始版本号
            int originStamp=demo.getStamp();
            System.out.println(Thread.currentThread().getName()+"获得的初始版本号为"+originStamp);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"修改demo的结果为"+demo.compareAndSet(5,101,originStamp,originStamp+1));
            System.out.println(Thread.currentThread().getName()+"当前demo的结果为"+demo.getReference());
        },"A").start();
        new Thread(()->{
            //获得初始版本号
            int originStamp=demo.getStamp();
            System.out.println(Thread.currentThread().getName()+"获得的初始版本号为"+originStamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(demo.compareAndSet(5,20,demo.getStamp(),demo.getStamp()+1));
            System.out.println(Thread.currentThread().getName()+"当前demo的结果为"+demo.getReference()+"当前版本号为"+demo.getStamp());
            System.out.println("===================================================================================================");
            System.out.println(demo.compareAndSet(20,5,demo.getStamp(),demo.getStamp()+1));
            System.out.println(Thread.currentThread().getName()+"当前demo的结果为"+demo.getReference()+"当前版本号为"+demo.getStamp());
        },"B").start();
    }
}
