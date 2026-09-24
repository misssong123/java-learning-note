package com.meng.datatype;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
    private static final String REDIS_IP = System.getenv().getOrDefault("REDIS_IP","test-teg-yxpt01.rdb.58dns.org");
    private static final Integer REDIS_PORT = Integer.parseInt(System.getenv().getOrDefault("REDIS_PORT","50029"));
    private static final String REDIS_PASSWORD = System.getenv().getOrDefault("REDIS_PASSWORD","3c78d83a7c0d8da0");
    private static final JedisPool jedisPool;
    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);          // 最大连接数
        poolConfig.setMaxIdle(10);           // 最大空闲连接
        poolConfig.setMinIdle(2);            // 最小空闲连接
        poolConfig.setTestOnBorrow(true);    // 借用连接时检测可用性
        jedisPool = new JedisPool(poolConfig, REDIS_IP, REDIS_PORT,2000, REDIS_PASSWORD);
    }
    /**
     * 获取 Jedis 实例，使用 try-with-resources 自动归还连接
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
    public static void returnResource(Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            jedis.close();
        }

    }
    public static void close() {
        if (jedisPool != null && !jedisPool.isClosed()) {
            jedisPool.close();
        }
    }
}
