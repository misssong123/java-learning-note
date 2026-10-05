package com.meng.datatype.d09streamdemo;

import com.meng.datatype.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XAddParams;
import redis.clients.jedis.params.XReadGroupParams;
import redis.clients.jedis.resps.StreamEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 概念
 * Stream 是 Redis 5.0 引入的专业消息队列类型。支持持久化、自动生成全局唯一 ID、
 * 消费组（Consumer Group）、消息 Ack 确认、
 * 消息挂起列表（PEL, Pending Entries List）以及消费重试等机制，补齐了以往 Pub/Sub 不能持久化的短板。
 *
 * 底层原理
 * 基于 Radix Tree（基数树/压缩前缀树） 存储消息文本，支持高效的范围查询与区间搜索。
 * 消息节点自带唯一 ID，格式为 <millisecondsTime>-<sequenceNumber>。
 */
public class StreamDemo {
    public static void main(String[] args) {
        StreamDemo demo = new StreamDemo();
        //demo.produceOrderEvent("order-001", "created");
        demo.consumeOrderEvents("group-001", "consumer-001");
    }
    // 场景 1：生产消息（追加发布日志/事件）
    public StreamEntryID produceOrderEvent(String orderId, String status) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String streamKey = "stream:orders";
            Map<String, String> body = new HashMap<>();
            body.put("orderId", orderId);
            body.put("status", status);

            // XADD 自动生成唯一 ID (*)
            StreamEntryID id = jedis.xadd(streamKey, XAddParams.xAddParams(), body);
            System.out.println("Published Event ID: " + id);
            return id;
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 2：消费者组（Consumer Group）轮询消费消息
    public void consumeOrderEvents(String groupName, String consumerName) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String streamKey = "stream:orders";

            // 创建消费组（如果已存在会报异常，实际场景中做 try-catch 包裹）
            try {
                jedis.xgroupCreate(streamKey, groupName, StreamEntryID.LAST_ENTRY, true);
            } catch (Exception ignored) {}

            // > 代表接收未经其他消费者读取过的最新消息
            Map<String, StreamEntryID> streamMap = new HashMap<>();
            streamMap.put(streamKey, StreamEntryID.UNRECEIVED_ENTRY);

            List<Map.Entry<String, List<StreamEntry>>> response = jedis.xreadGroup(
                    groupName,
                    consumerName,
                    XReadGroupParams.xReadGroupParams().count(1).block(2000),
                    streamMap
            );

            if (response != null) {
                for (Map.Entry<String, List<StreamEntry>> stream : response) {
                    for (StreamEntry entry : stream.getValue()) {
                        System.out.println("Processing: " + entry.getFields());
                        // 确认消息处理完毕 (ACK)
                        jedis.xack(streamKey, groupName, entry.getID());
                    }
                }
            }
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 3：获取 pending 消息（处理崩溃重启后的未确认消息重试）
    public void processPendingEvents(String groupName, String consumerName) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String streamKey = "stream:orders";
            // 从 ID "0-0" 开始，读取该消费者已经接收但尚未 ACK 的消息
            Map<String, StreamEntryID> streamMap = new HashMap<>();
            streamMap.put(streamKey, new StreamEntryID("0-0"));

            List<Map.Entry<String, List<StreamEntry>>> response = jedis.xreadGroup(
                    groupName,
                    consumerName,
                    XReadGroupParams.xReadGroupParams().count(10),
                    streamMap
            );

            if (response != null) {
                for (Map.Entry<String, List<StreamEntry>> stream : response) {
                    for (StreamEntry entry : stream.getValue()) {
                        System.out.println("Retrying Pending Msg: " + entry.getID());
                        // 补逻辑重试后 Ack
                        jedis.xack(streamKey, groupName, entry.getID());
                    }
                }
            }
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }
}
