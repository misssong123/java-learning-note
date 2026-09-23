package com.example.java.forkjoinpooldemo;

import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolDemo {
    public static void main(String[] args) {
    }
}
class SumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10000; // 拆分阈值
    private long[] array;
    private int start, end;

    public SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // 足够小，直接计算
            long sum = 0;
            for (int i = start; i < end; i++) sum += array[i];
            return sum;
        } else {
            // 拆分任务
            int mid = (start + end) / 2;
            SumTask left = new SumTask(array, start, mid);
            SumTask right = new SumTask(array, mid, end);

            left.fork(); // 异步执行左边
            Long rightResult = right.compute(); // 同步执行右边，节约一个线程
            Long leftResult = left.join(); // 等待结果

            return leftResult + rightResult;
        }
    }
}