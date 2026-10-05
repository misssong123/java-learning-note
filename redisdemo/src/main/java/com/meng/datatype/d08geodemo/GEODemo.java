package com.meng.datatype.d08geodemo;

import com.meng.datatype.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.args.GeoUnit;
import redis.clients.jedis.resps.GeoRadiusResponse;

import java.util.List;

/**
 * 概念
 * GEO 主要用于存储地理位置信息（经度 Longitude、纬度 Latitude、名称 Member），
 * 并支持两点间距离计算、范围查找等功能。
 *
 * 底层原理
 * GEO 本质上依然是 ZSet（有序集合）：
 * 内部利用 GeoHash 算法，将二维的经纬度坐标编码（空间填充曲线，如 Peano / Hilbert 曲线）
 * 转化为一个 52 位的 64 位无符号整数。
 * 将该整数存入 ZSet 作为 score，使得地理位置相邻的点在整数空间上也大致连续，进而转化为 ZSet 的范围查询。
 */
public class GEODemo {
    public static void main(String[] args) {
        GEODemo demo = new GEODemo();
        //demo.calculateDistance();
        //demo.findNearbyPops();
        demo.getGeoHash("Beijing");
    }
    // 场景 1：添加与计算两点间的地理距离
    public void calculateDistance() {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "geo:cities";
            // 增加位置 (经度, 纬度, 名称)
            jedis.geoadd(key, 116.405285, 39.904989, "Beijing");
            jedis.geoadd(key, 121.472644, 31.231706, "Shanghai");

            // 计算两地相距公里数
            Double distance = jedis.geodist(key, "Beijing", "Shanghai", GeoUnit.KM);
            System.out.println("Distance: " + distance + " km");
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 2：查找附近的人（以某城市/某点为中心半径检索）
    public void findNearbyPops() {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "geo:drivers";
            jedis.geoadd(key, 116.400000, 39.900000, "DriverA");
            jedis.geoadd(key, 116.402000, 39.903000, "DriverB");

            // 查找半径 5 公里内的司机
            List<GeoRadiusResponse> drivers = jedis.georadius(
                    key, 116.405285, 39.904989, 5.0, GeoUnit.KM
            );
            for (GeoRadiusResponse driver : drivers) {
                System.out.println("Found Driver: " + driver.getMemberByString());
            }
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }

    // 场景 3：获取某个 member 的 GeoHash 字符串
    public void getGeoHash(String member) {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();
            String key = "geo:cities";
            List<String> hashes = jedis.geohash(key, member);
            System.out.println("GeoHash for " + member + ": " + hashes.get(0));
        }finally {
            JedisUtil.returnResource(jedis);
        }

    }
}
