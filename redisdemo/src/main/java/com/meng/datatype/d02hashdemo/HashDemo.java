package com.meng.datatype.d02hashdemo;

import com.meng.datatype.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * 概念
 * Hash 是一个键值对（field-value）的集合，
 * 类似于 Java 中的 Map<String, String>，特别适合存储结构化的对象数据。
 *
 * 底层原理
 * 根据数据量自动切换内部编码：
 * 1.ZipList / Listpack：当哈希中元素数量较少（默认 $< 512$ 个）
 *      且每个元素值较小（默认 $< 64$ 字节）时，使用紧凑的连续内存存储，节省空间。
 * 2.HashTable：超出阈值后，转换为标准哈希表，提供 $O(1)$ 的查找复杂度，采用链地址法解决冲突。
 */
public class HashDemo {
    /**
     * 用户信息/对象存储：相比 String 保存 JSON，Hash 支持对单个属性修改，无需序列化整个对象。
     *
     * 电商购物车：用 Cart:UserId 作为 Key，SkuId 作为 Field，Quantity 作为 Value。
     *
     * 配置项/状态管理：存储开关状态、系统参数设置等。
     * @param args
     */
    public static void main(String[] args) {
        HashDemo demo = new HashDemo();
        //demo.handleUserProfile("test");
        //demo.manageShoppingCart("test", "10086", 1);
        demo.updateSystemConfig("test", "max_connections", "1000");
    }
    // 场景 1：存储和更新用户对象
    public void handleUserProfile(String userId) {
        Jedis jedis = null;
        try{
            jedis = JedisUtil.getJedis();
            String key = "user:" + userId;
            jedis.hset(key, "name", "Alex");
            jedis.hset(key, "age", "28");
            // 单独增减属性
            jedis.hincrBy(key, "age", 1);
            Map<String, String> userMap = jedis.hgetAll(key);
            System.out.println("User Profile: " + userMap);
        }finally {
            //归还
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 2：电商购物车管理
    public void manageShoppingCart(String userId, String skuId, int count) {
        Jedis jedis = null;
        try{
            jedis = JedisUtil.getJedis();
            String cartKey = "cart:" + userId;
            // 增加商品数量，若 field 不存在则新建
            jedis.hincrBy(cartKey, skuId, count);

            // 获取特定商品的数量
            String quantity = jedis.hget(cartKey, skuId);
            System.out.println("SKU: " + skuId + ", Count: " + quantity);
        }finally {
            //归还
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 3：系统参数动态更新
    public void updateSystemConfig(String configGroup, String paramKey, String paramValue) {
        Jedis jedis = null;
        try{
            jedis = JedisUtil.getJedis();
            String key = "sys:config:" + configGroup;
            jedis.hset(key, paramKey, paramValue);
            String currentVal = jedis.hget(key, paramKey);
            System.out.println("Config [" + paramKey + "] set to: " + currentVal);
        }finally {
            //归还
            JedisUtil.returnResource(jedis);
        }
    }
}
