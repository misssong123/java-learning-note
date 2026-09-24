package com.meng.datatype.d03listdemo;

import com.meng.datatype.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * 概念
 * List 是按插入顺序排序的字符串列表，可以在头部（left）或尾部（right）插入元素，支持双向插入与弹出。
 *
 * 底层原理
 * 在早期版本中使用 ZipList 与 LinkedList。
 * Redis 3.2 之后统一使用 QuickList（双向链表与 Listpack 的结合体），
 * 既保障了链表高效的头尾插入能力（$O(1)$），又减少了链表节点的指针开销。
 */
public class ListDemo {
    public static void main(String[] args) {
        ListDemo demo = new ListDemo();
        //demo.taskQueueExample();
        /*for (int i = 0; i < 15; i++) {
            demo.pushUserTimeline("meng", "hello world " + i);
        }*/
        demo.getComments("1001",1,10);
    }
    // 场景 1：异步任务队列生产与消费
    public void taskQueueExample() {
        Jedis jedis = null;
        try{
            jedis = JedisUtil.getJedis();
            String queueKey = "task:queue";
            // 生产者 push 任务
            jedis.lpush(queueKey, "task_001", "task_002");

            // 消费者 pop 任务（阻塞弹出，超时时间 5 秒）
            List<String> poppedTask = jedis.brpop(5, queueKey);
            for (String task : poppedTask) {
                System.out.println(task);
            }
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 2：最新动态/消息列表（只保留最新 N 条）
    public void pushUserTimeline(String userId, String statusId) {
        Jedis jedis = null;
        try{
            jedis = JedisUtil.getJedis();
            String key = "timeline:" + userId;
            jedis.lpush(key, statusId);
            // 剪裁列表，只保留前 10 个最新元素
            jedis.ltrim(key, 0, 9);
            List<String> latest = jedis.lrange(key, 0, -1);
            System.out.println("Latest Statuses: " + latest);
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 3：文章评论列表（正序/倒序翻页获取）
    public void getComments(String articleId, long page, long pageSize) {
        Jedis jedis = null;
        try{
            jedis = JedisUtil.getJedis();
            String key = "article:comments:" + articleId;
            long start = (page - 1) * pageSize;
            long stop = start + pageSize - 1;

            List<String> pageComments = jedis.lrange(key, start, stop);
            System.out.println("Page " + page + " Comments: " + pageComments);
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }
}
