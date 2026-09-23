package com.example.java.volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *该类展示volatile关键字不能保证原子性
 */
public class VolatileAtomicDemo {
    public static void main(String[] args) {
        Demo2 demo=new Demo2();
        //使用20个线程,每个线程执行1000次addData方法，测试volatile原子性
        for(int i = 0; i < 20; i++){
            new Thread(new Runnable() {
                public void run() {
                    for(int j=0;j<1000;j++) {
                        demo.addData();
                        demo.addDataAtomic();
                    }
                }
            }).start();
        }
        //保证只存在main线程和gc线程
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(demo.data);
        System.out.println(demo.dataCompare);
    }
}

/**
 * 该类为样例类，用于原子性的展示
 */
class Demo2{
    //添加volatile关键字测试原子性
    volatile int data=0;
    //使用原子类进行volatile的比较
    AtomicInteger dataCompare=new AtomicInteger(0);
    /**
     * data数据自增1
     */
    public void addData(){
        data++;
    }
    /**
     * data数据自增1
     */
    public void addDataAtomic(){
        dataCompare.getAndIncrement();
    }
}