package com.example.java.aqsdemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AQSDemo {
    public static void main(String[] args) throws Exception {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock().lock();
        readWriteLock.readLock().unlock();
        System.out.println("read");
        readWriteLock.writeLock().lock();
        System.out.println("write");
        readWriteLock.writeLock().unlock();
        CountDownLatch countDownLatch = new CountDownLatch(0);
        countDownLatch.countDown();
        countDownLatch.await();
    }
}
class SemaphoreParkingDemo {

    public static void main(String[] args) {
        // 1. 初始化信号量：3个许可（车位）
        // 第二个参数为 true 表示公平锁，严格按照排队顺序进入
        Semaphore parkingSlots = new Semaphore(3, true);

        // 模拟 6 辆车
        for (int i = 1; i <= 6; i++) {
            final int carId = i;
            new Thread(() -> {
                try {
                    System.out.println("🚗 车辆 " + carId + " 到达停车场，尝试进入...");

                    // 2. 尝试获取许可
                    parkingSlots.acquire();

                    System.out.println("✅ 车辆 " + carId + " 成功进入车位！");

                    // 模拟停车时长
                    long parkingTime = (long) (Math.random() * 5 + 1);
                    TimeUnit.SECONDS.sleep(parkingTime);

                    System.out.println("⬅️ 车辆 " + carId + " 离开，停留了 " + parkingTime + " 秒。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 3. 释放许可（必须放在 finally 中）
                    parkingSlots.release();
                }
            }, "CarThread-" + i).start();
        }
    }
}