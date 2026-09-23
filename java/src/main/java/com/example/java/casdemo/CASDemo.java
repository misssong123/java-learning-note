package com.example.java.casdemo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * unsafe类
 * ABA 问题
 * 自旋开销问题
 * AtomicReference
 */
public class CASDemo {
    public static void main(String[] args) {
        atomicStampedReference();
    }
    //AtomicInteger
    public static void atomicInteger() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.compareAndSet(1, 2);
        atomicInteger.addAndGet(1);
    }
    //AtomicStampedReference ABA问题
    public static void atomicStampedReference() {
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(1, 1);
        //变更
        atomicStampedReference.compareAndSet(1, 2, 1, 2);
        //恢复
        atomicStampedReference.compareAndSet(2, 1, 2, 1);
        System.out.println(atomicStampedReference.getReference());
        System.out.println(atomicStampedReference.getStamp());
    }
    //自旋开销问题 -LongAdder
    public static void spin() {
        // 初始化
        LongAdder counter = new LongAdder();
        // 并发累加（线程安全且低竞争）
        counter.increment();
        // 获取最终结果
        long total = counter.sum();
    }
    // 2. 使用 AtomicReference 包装上下文
    private static final AtomicReference<TaskContext> contextRef =
            new AtomicReference<>(new TaskContext("IDLE", 0));
    //AtomicReference
    public static void atomicReference(int newProgress) {
        TaskContext oldContext;
        TaskContext newContext;
        do {
            oldContext = contextRef.get(); // 获取当前引用
            // 基于旧状态创建新对象（函数式编程思想）
            newContext = new TaskContext("RUNNING", newProgress);

            // 3. CAS 替换：只有当引用没变时才更新
        } while (!contextRef.compareAndSet(oldContext, newContext));

    }
}
class TaskContext {
    final String status;
    final int progress;

    TaskContext(String status, int progress) {
        this.status = status;
        this.progress = progress;
    }
}
