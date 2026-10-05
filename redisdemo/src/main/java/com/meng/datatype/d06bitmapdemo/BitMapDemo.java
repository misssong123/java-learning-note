package com.meng.datatype.d06bitmapdemo;

import com.meng.datatype.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.args.BitOP;

/**
 * 概念
 * BitMap 本质上并不是独立的内部数据结构，而是基于 String 类型的位操作。
 * 它以 Bit（0 或 1）为单位存储，极大地节省了存储空间。保存 1 亿个用户的打卡记录只需约 12MB 内存。
 *
 * 底层原理
 * 底层依旧是 SDS。位图的指令（如 SETBIT, GETBIT, BITCOUNT）
 * 直接对 SDS 中存储的字节数组中的每一个 bit 进行寻址和偏移量（Offset）读写。
 */
public class BitMapDemo {
    public static void main(String[] args) {
        BitMapDemo demo = new BitMapDemo();
        //demo.userCheckIn("1001", 1);
        //System.out.println(demo.getAnnualCheckInCount("1001"));
        System.out.println(demo.calculateDAURetention("2026-09", "2026-10"));
    }
    // 场景 1：用户打卡/签到
    public void userCheckIn(String userId, int dayOfYear) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "user:checkin:" + userId + ":2026";
            // 将第 dayOfYear 天对应的位设为 1
            jedis.setbit(key, dayOfYear, true);

            // 校验某天是否打卡
            boolean isChecked = jedis.getbit(key, dayOfYear);
            System.out.println("Day " + dayOfYear + " checked in? " + isChecked);
        }finally {
            JedisUtil.returnResource(jedis);
        }
    }

    // 场景 2：统计年度打卡总天数
    public long getAnnualCheckInCount(String userId) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "user:checkin:" + userId + ":2026";
            // 统计二进制中 1 的个数
            return jedis.bitcount(key);
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 3：日活跃用户（DAU）留存分析（按位求交集）
    public long calculateDAURetention(String date1, String date2) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key1 = "dau:" + date1;
            String key2 = "dau:" + date2;
            String destKey = "dau:retained:" + date1 + "_" + date2;

            // 对两个 BitMap 进行 AND 位运算，结果存在 destKey
            jedis.bitop(BitOP.AND, destKey, key1, key2);
            return jedis.bitcount(destKey);
        }finally {
            JedisUtil.returnResource(jedis);
        }
    }
}
