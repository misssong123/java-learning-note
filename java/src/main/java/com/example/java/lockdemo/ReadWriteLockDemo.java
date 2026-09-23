package com.example.java.lockdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁样例
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Demo1 demo=new Demo1();
        for(int i = 0; i <5; i++){
            final int temp=i;
            new Thread(new Runnable() {
                public void run() {
                    demo.put(temp+"",temp+"");
                }
            },""+i).start();
        }
        for(int i = 0; i <5; i++){
            final int temp=i;
            new Thread(new Runnable() {
                public void run() {
                    demo.get(temp+"");
                }
            },""+i).start();
        }
    }

}

/**
 * 模拟缓存
 * 1.存取
 * 2.获取
 * 3.清除
 */
class Demo{
    Map<String,String>map=new HashMap<>();
    public void put(String key,String value){
        System.out.println(Thread.currentThread().getName()+"开始写入。。。");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入完成");
    }
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"开始读取。。。");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"读取结果为:"+map.get(key));
    }
}
class Demo1{
    Map<String,String>map=new HashMap<>();
    ReadWriteLock lock=new ReentrantReadWriteLock();
    public void put(String key,String value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入。。。");
            TimeUnit.SECONDS.sleep(1);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
    public void get(String key){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始读取。。。");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"读取结果为:"+map.get(key));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }
}
/**
 * Demo结果
 * 0开始写入。。。
 * 3开始写入。。。
 * 4开始写入。。。
 * 1开始写入。。。
 * 2开始写入。。。
 * 1开始读取。。。
 * 0开始读取。。。
 * 2开始读取。。。
 * 3开始读取。。。
 * 4开始读取。。。
 * 2读取结果为:null
 * 3读取结果为:3
 * 0读取结果为:0
 * 0写入完成
 * 4读取结果为:null
 * 1写入完成
 * 2写入完成
 * 1读取结果为:1
 * 4写入完成
 * 3写入完成
 */
/**Demo1结果
 *0开始写入。。。
 * 0写入完成
 * 1开始写入。。。
 * 1写入完成
 * 2开始写入。。。
 * 2写入完成
 * 4开始写入。。。
 * 4写入完成
 * 3开始写入。。。
 * 3写入完成
 * 0开始读取。。。
 * 1开始读取。。。
 * 2开始读取。。。
 * 3开始读取。。。
 * 4开始读取。。。
 * 0读取结果为:0
 * 1读取结果为:1
 * 4读取结果为:4
 * 2读取结果为:2
 * 3读取结果为:3
 */
