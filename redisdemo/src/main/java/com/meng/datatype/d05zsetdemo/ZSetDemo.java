package com.meng.datatype.d05zsetdemo;

import com.meng.datatype.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.Tuple;

import java.util.List;

/**
 * 概念
 * 在 Set 的基础上，为每个元素赋予一个 score（分值），
 * Redis 自动根据 score 为元素进行从小到大的排序。
 * 元素不可重复，但 score 可以重复。
 *
 * 底层原理
 * Listpack：当元素数量少且长度小时使用，压缩存取。
 * SkipList（跳表） + HashTable：
 * SkipList：通过多层随机索引链表，实现类似二分查找的功能，插入、删除、查询时间复杂度均为 $O(\log N)$。
 * HashTable：配合跳表存储 member -> score 映射，以 $O(1)$ 复杂度查询任意成员的分数。
 */
public class ZSetDemo {
    // 场景 1：实时热搜/积分排行榜
    public void leaderboardExample() {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "leaderboard:game";
            jedis.zadd(key, 1200, "player1");
            jedis.zadd(key, 1550, "player2");
            jedis.zadd(key, 1100, "player3");

            // 获取 Top 2（按分数从高到低）
            List<Tuple> topPlayers = jedis.zrevrangeWithScores(key, 0, 1);
            for (Tuple t : topPlayers) {
                System.out.println(t.getElement() + " : " + t.getScore());
            }
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 2：延迟任务队列
    public void delayQueueProducerAndConsumer() {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "delay:tasks";
            long executeTime = System.currentTimeMillis() + 5000; // 5秒后执行

            // 生产者：添加任务，Score 为预期执行的时间戳
            jedis.zadd(key, executeTime, "task_id_9988");

            // 消费者：拉取分值小于等于当前时间的到期任务
            long now = System.currentTimeMillis();
            List<String> dueTasks = jedis.zrangeByScore(key, 0, now);
            if (!dueTasks.isEmpty()) {
                for (String taskId : dueTasks) {
                    // 确保多节点抢占原子的安全性，尝试移除成功即抢到任务
                    if (jedis.zrem(key, taskId) > 0) {
                        System.out.println("Processing delay task: " + taskId);
                    }
                }
            }
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 3：滑动窗口限流
    public boolean isAllowed(String userId, String actionKey, int maxCount, int windowSeconds) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "rate:limit:" + userId + ":" + actionKey;
            long now = System.currentTimeMillis();
            long windowStart = now - (windowSeconds * 1000L);

            // 清理窗口之前的记录
            jedis.zremrangeByScore(key, 0, windowStart);
            // 获取当前窗口内的请求数
            long currentCount = jedis.zcard(key);
            if (currentCount < maxCount) {
                jedis.zadd(key, now, String.valueOf(now));
                jedis.expire(key, windowSeconds); // 防止无用 key 长期残留
                return true;
            }
            return false;
        }finally {
            JedisUtil.returnResource(jedis);
        }
    }
}
