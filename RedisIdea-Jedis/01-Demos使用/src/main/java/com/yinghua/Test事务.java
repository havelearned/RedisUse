package com.yinghua;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class Test事务 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        jedis.flushDB();
        //准备数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","zhangsan");
        jsonObject.put("age","231");
        String result = jsonObject.toJSONString();
        System.out.println(jsonObject.get("age"));

        //加上乐观锁,当result发生变化事务处理失败
        jedis.watch(result);

        //开启事务
        Transaction multi = jedis.multi();




        try {
            //抢夺数据
            jsonObject.put("age","123123123123123");

            Thread.sleep(1000);
            multi.set("user",result);
            multi.set("user2",result);


//            int i=1/0;// 测试失败的事务
            multi.exec();//执行事务
        }catch(Exception e){
            multi.discard();//放弃事务
            jedis.unwatch();//放弃监控获取最新的值
            e.printStackTrace();
        }
        finally {
            System.out.println(jedis.get("user"));
            System.out.println(jedis.get("user2"));
            jedis.close();//连接

        }



    }
}
