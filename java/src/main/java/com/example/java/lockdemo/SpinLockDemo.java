package com.example.java.lockdemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁样例
 */
public class SpinLockDemo {
    public static void main(String[] args) {
        MyLock lock=new MyLock();
        new Thread(()->{
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unLock();
        },"AAA").start();
        new Thread(()->{
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unLock();
        },"BBB").start();
    }
}
class MyLock{
    AtomicReference<Thread> demo=new AtomicReference<>();
    public void lock(){
        System.out.println(Thread.currentThread().getName()+":准备获取锁");
        while (!demo.compareAndSet(null,Thread.currentThread())){

        }
        System.out.println(Thread.currentThread().getName()+":获取锁....");
    }
    public void unLock(){
        System.out.println(Thread.currentThread().getName()+":释放锁....");
        demo.compareAndSet(Thread.currentThread(),null);

    }
}
/**
 * AAA:准备获取锁
 * BBB:准备获取锁
 * AAA:获取锁....
 * AAA:释放锁....
 * BBB:获取锁....
 * BBB:释放锁....
 */
