package com.meng.datatype.d04setdemo;

import com.meng.datatype.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * 概念
 * Set 是无序且不重复的字符串集合，支持集合内的快速查找、去重以及集合间的求交集（SINTER）、并集（SUNION）、差集（SDIFF）。
 *
 * 底层原理
 * 根据数据类型和数量自动转换：
 * 1.IntSet：当元素全为整数且数量较少（默认 $< 512$ 个）时采用连续数组存储，查询使用二分查找。
 * 2.HashTable：当包含非整数或数据量较大时，退化为标准哈希表（Value 统一为 NULL）。
 */
public class SetDemo {
    public static void main(String[] args) {
        SetDemo demo = new SetDemo();
        demo.addArticleTags("article:1", "java", "redis", "mysql", "spring","springboot","java");
    }
    // 场景 1：共同关注/共同好友（交集操作）
    public void findCommonFollows(String userA, String userB) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String keyA = "user:" + userA + ":follows";
            String keyB = "user:" + userB + ":follows";
            Set<String> common = jedis.sinter(keyA, keyB);
            System.out.println("Common Follows: " + common);
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 2：抽奖程序（随机抽取与去重）
    public void drawWinners(String activityId, int winnerCount) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String poolKey = "lottery:activity:" + activityId + ":candidates";
            // 随机弹出 winnerCount 个中奖者（已中奖不可重复中）,SRANDMEMBER（不去除）SPOP（去除）
            List<String> winners = jedis.srandmember(poolKey, winnerCount);
            //Set<String> winners = jedis.spop(poolKey, winnerCount);
            System.out.println("Winners: " + winners);
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 3：唯一标签管理
    public void addArticleTags(String articleId, String... tags) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "article:" + articleId + ":tags";
            jedis.sadd(key, tags);
            Set<String> allTags = jedis.smembers(key);
            System.out.println("Article Tags: " + allTags);
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }
}
