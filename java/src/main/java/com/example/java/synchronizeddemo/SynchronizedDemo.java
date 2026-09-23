package com.example.java.synchronizeddemo;

import org.openjdk.jol.info.ClassLayout;

public class SynchronizedDemo {
    public static void main(String[] args) throws Exception {
        try { Thread.sleep(5000); } catch (Exception e) {}

        Object obj = new Object();
        // 1. 无锁状态打印
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj) {
            // 2. 轻量级锁/偏向锁状态打印
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }

        // 3. 模拟并发产生重量级锁
        new Thread(() -> {
            synchronized (obj) {
                try { Thread.sleep(2000); } catch (Exception e) {}
            }
        }).start();

        Thread.sleep(100); // 确保上面的线程先拿到锁

        synchronized (obj) {
            // 4. 重量级锁打印
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}
