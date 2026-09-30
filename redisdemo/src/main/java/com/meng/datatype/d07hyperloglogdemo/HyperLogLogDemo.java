package com.meng.datatype.d07hyperloglogdemo;

import com.meng.datatype.JedisUtil;
import redis.clients.jedis.Jedis;

/**
 * 概念
 * HyperLogLog 是用于大基数（Cardinaty）估算的数据结构。
 * 它只需 12KB 的固定内存，就能对多达 $2^{64}$ 个元素进行概率去重统计，
 * 标准标准误差仅为 0.81%。注意：它不保存原始元素，仅能回答“基数估计值是多少”，不可取出原数据。
 *
 * 底层原理
 * 基于概率学中的 伯努利过程（Bernoulli Process） 与极大似然估计：
 * 将输入的元素通过 Hash 函数映射成 64 位比特串。观察二进制串首个 1 出现的位置 $k$。
 * 利用 16384 个桶（Registers）分桶统计极值，最终使用调和平均数消除偏差，推算出整体唯一元素的概率数量。
 */
public class HyperLogLogDemo {
    // 场景 1：记录并统计页面 UV
    public void recordPageUV(String pageId, String ipOrUserId) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "uv:page:" + pageId;
            jedis.pfadd(key, ipOrUserId);

            long estimatedUV = jedis.pfcount(key);
            System.out.println("Estimated Page UV: " + estimatedUV);
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 2：合并多日 UV（月度/周度总 UV 估算）
    public long getWeeklyUV(String... days) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String[] keys = new String[days.length];
            for (int i = 0; i < days.length; i++) {
                keys[i] = "uv:page:home:" + days[i];
            }
            String destKey = "uv:page:home:weekly";
            // 合并多个 HyperLogLog 到新 Key
            jedis.pfmerge(destKey, keys);
            return jedis.pfcount(destKey);
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 3：海量搜索词去重基数估算
    public void logSearchKeywords(String searchKeyword) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "hll:search:keywords";
            jedis.pfadd(key, searchKeyword);
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }
}
