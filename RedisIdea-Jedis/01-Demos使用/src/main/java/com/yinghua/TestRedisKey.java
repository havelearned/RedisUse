package com.yinghua;

import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.Set;

/**
 * Hello world!
 */
public class TestRedisKey {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //Redis的命令就是 Jedis的方法
        System.out.println("是否连接成功:"+jedis.ping());
        System.out.println("判断某个key是否存在:"+jedis.exists("name"));
        System.out.println("清空所有key和value:"+jedis.flushAll());
        System.out.println("清空数据库key和value:"+jedis.flushDB());
        System.out.println("新增键对值:"+jedis.set("username","zhangsan"));
        System.out.println("新增键对值:"+jedis.set("age","34"));
        System.out.println("获取所有的key value:");
        Set<String> keys = jedis.keys("*");
        System.out.println(Arrays.toString(keys.toArray()));

        System.out.println("删除key:"+jedis.del("age"));
        System.out.println("判断key是否存在:"+jedis.exists("name"));
        System.out.println("查看key的value是什么存储类型:"+jedis.type("name"));
        System.out.println("随机返回key空间的一个:"+jedis.randomKey());
        System.out.println("重命名key:"+jedis.rename("username","name"));
        System.out.println("获取key:"+jedis.get("name"));
        System.out.println("按索引查询"+jedis.select(0));
        System.out.println("返回当前数据库中所有key的个数:"+jedis.dbSize());



    }
}
