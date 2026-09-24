package com.meng.datatype.d01stringdemo;

import com.meng.datatype.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * 概念
 * String 是 Redis 最基础的数据结构，Key 和 Value 均以字符串形式存储。一个键最大能存储 512MB 的数据，
 * 除了普通文本，还可以存储二进制数据（如图片、序列化对象、JSON 字符串）。
 *
 * 底层原理
 * 底层采用 SDS（Simple Dynamic String，简单动态字符串） 实现：
 * 长度预分配与空间惰性释放：避免频繁内存重分配。
 * 常数复杂度获取长度：内部维护 len 字段，获取字符串长度复杂度为 $O(1)$。
 * 二进制安全：以字节数组形式存储（char buf[]），不依赖字符串终止符 \0。
 */
public class StringDemo {
    /**
     * 热点数据缓存：存储 JSON 序列化的对象或 HTML 片段。
     *
     * 分布式锁：使用 SET key value NX PX timeout 实现互斥。
     *
     * 全局计数器/限流：使用 INCR / DECR 记录文章阅读量、接口调用频次。
     * @param args
     */
    public static void main(String[] args) {
        StringDemo demo = new StringDemo();
        // 场景 1：缓存对象（JSON序列化）
        demo.cacheUserProfile("1", "{\"name\":\"张三\",\"age\":20}");
        // 场景 2：分布式锁
        demo.acquireLock("lock:order", "1000", 2000);
        // 场景 3：全局计数器/限流
        demo.incrementArticleViews("10086");
    }
    // 场景 1：缓存对象（JSON序列化）
    public void cacheUserProfile(String userId, String userJson) {
        Jedis jedis = null;
        try{
            jedis = JedisUtil.getJedis();
            String key = "user:profile:" + userId;
            // 设置缓存，过期时间为 3600 秒
            jedis.setex(key, 10, userJson);
            System.out.println("Cached User: " + jedis.get(key));
        }finally {
            // 关闭连接
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 2：分布式锁实现
    public boolean acquireLock(String lockKey, String requestId, int expireTimeMillis) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            SetParams params = SetParams.setParams().nx().px(expireTimeMillis);
            String result = jedis.set(lockKey, requestId, params);
            return "OK".equalsIgnoreCase(result);
        }finally {
            // 关闭连接
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 3：计数器与限流（如文章阅读数）
    public long incrementArticleViews(String articleId) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "article:views:" + articleId;
            long views = jedis.incr(key);
            // 如果是首次计数，可设置自动清理（如限制1小时内计数）
            if (views == 1) {
                jedis.expire(key, 10);
            }
            return views;
        }finally {
            // 关闭连接
            JedisUtil.returnResource(jedis);
        }
    }
}
