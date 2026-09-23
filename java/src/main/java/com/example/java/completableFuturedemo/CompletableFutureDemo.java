package com.example.java.completableFuturedemo;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *① 创建任务 (Initiating)
 * runAsync(Runnable): 无返回值，适合触发不需要反馈的操作（如发送埋点）。
 *
 * supplyAsync(Supplier): 有返回值，是异步加载数据的最常用入口。
 *
 * ② 链式回调 (Applying/Running)
 * thenApply / thenApplyAsync: 转换。上一步结果作为入参，产生新结果（类似 Stream 的 map）。
 *
 * thenAccept / thenRun: 消耗。只用结果不返回，或纯粹在结束后运行。
 *
 * ③ 组合编排 (Combining/Composing)
 * thenCompose: 打平（FlatMap）。如果回调函数本身也返回 CF，用它防止嵌套 CF<CF<T>>。
 *
 * thenCombine: 聚合。等待两个独立 CF 都完成，然后合并结果。
 *
 * allOf / anyOf: 多路复用。等待所有完成或任意一个完成。
 *
 * ④ 异常处理 (Exception Handling)
 * exceptionally: 异常捕获并返回默认值。
 *
 * handle: 无论成败都执行，可获取结果和异常，灵活性最高。
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {

    }
    public void test() {
        // 1. 线程池隔离，防止慢查询拖垮全局
        ExecutorService detailPool = Executors.newFixedThreadPool(10);

        CompletableFuture<Info> infoTask = CompletableFuture.supplyAsync(Info::new, detailPool);
        CompletableFuture<Stock> stockTask = CompletableFuture.supplyAsync(Stock::new, detailPool);

        // 2. 组合逻辑：并行执行，全部完成后聚合
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(infoTask, stockTask);

        // 3. 异步获取结果
        allTasks.thenRun(() -> {
            Info info = infoTask.join();
            Stock stock = stockTask.join();
            // 渲染页面
        }).exceptionally(ex -> {
            System.out.println("error: " + ex.getMessage());
            return null;
        });
    }
}
 class Info {
     //
 }
 class Stock {
     //
 }
