package com.example.java.lockdemo;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁样例
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone=new Phone();
        new Thread(()->{
            phone.sendMessage();
        },"AAA").start();
        new Thread(()->{
            phone.sendMessage();
        },"AAA").start();
    }
}
class  Phone{
    public synchronized  void sendMessage()  {
        System.out.println(Thread.currentThread().getName()+":invoke sendMessage()");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendEmil();
    }
    public synchronized void sendEmil(){
        System.out.println(Thread.currentThread().getName()+":invoke sendEmil()");
    }
}
/**
 * 结果
 * AAA:invoke sendMessage()
 * AAA:invoke sendEmil()
 * AAA:invoke sendMessage()
 * AAA:invoke sendEmil()
 */

