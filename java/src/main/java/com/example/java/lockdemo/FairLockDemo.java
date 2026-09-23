package com.example.java.lockdemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁和非公平锁样例
 * ReentrantLock默认为非公平锁
 * synchronized为非公平锁
 */
public class FairLockDemo {
    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
    }
}
/**
 *Creates an instance of {@code ReentrantLock}.
 *This is equivalent to using {@code ReentrantLock(false)}.
  public ReentrantLock(){
        *sync=new NonfairSync();
        *}
 *   public ReentrantLock(boolean fair) {
 *         sync = fair ? new FairSync() : new NonfairSync();
 *     }
 */

