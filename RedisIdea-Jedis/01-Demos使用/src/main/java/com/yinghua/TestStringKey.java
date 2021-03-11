package com.yinghua;

import redis.clients.jedis.Jedis;

public class TestStringKey {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        jedis.flushDB();
        System.out.println("----------------------------------------");
        System.out.println("设置key1 value1:"+jedis.set("key1","value1"));
        System.out.println("设置key2 value2:"+jedis.set("key2","value2"));
        System.out.println("设置key3 value3:"+jedis.set("key3","value3"));

        System.out.println("删除key2:"+jedis.del("key2"));
        System.out.println("获取key2:"+jedis.get("key2"));

        System.out.println("修改key1:"+jedis.set("key1","zhangsan"));
        System.out.println("获取key1的值:"+jedis.get("key1"));
        System.out.println("在key3后面加入值:"+jedis.append("key4","value4"));
        System.out.println("key3的值:"+jedis.get("key3"));
        System.out.println("增加多个key value"+jedis.mset("name1","lishi","name2","zaoliu","name3","tianqi"));
        System.out.println("获取多个key value"+jedis.mget("name1","name2","name3"));
        System.out.println("删除多个key value"+jedis.del("name1","name2","name3"));


        System.out.println(jedis.flushDB());
        System.out.println("====================新增key value对防止覆盖原先的值====================");
        System.out.println("key1 有值就取消,没有值就添加:"+jedis.setnx("key1","vlaue1"));
        System.out.println("key2 有值取消操作,没有值就添加:"+jedis.setnx("key2","value2"));
        System.out.println("key2:"+jedis.setnx("key2","value3"));


        System.out.println("----------------------------------------");
    }
}
