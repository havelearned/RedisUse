package com.yinghua;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yinghua.bean.User;
import com.yinghua.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class DemoApplicationTests {

    //如果出现多个 redisTemplate 名字,使用别名
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;


    @Test
    public void Test(){
        redisUtil.set("name","app");
        System.out.println(redisUtil.get("name"));

    }


    @Test
    public void Test02(){
        redisTemplate.opsForValue().set("user","zhangsan");
        System.out.println(redisTemplate.opsForValue().get("user"));

    }


    @Test
    void contextLoads() {
        /**
         *  //下面的可以自己封装一个工具类 ,方便调用
         *  与命令一样的操作
         *       redisTemplate.opsForValue();  操作字符串
         *         redisTemplate.opsForList();
         *         redisTemplate.opsForSet();
         *         redisTemplate.opsForZSet();
         *         redisTemplate.opsForHash();
         *         redisTemplate.opsForGeo();
         *         redisTemplate.opsForHyperLogLog();
         * */

        //连接
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        System.out.println(connection.ping());

        redisTemplate.opsForValue().set("name","张三");

        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);


    }


    /**
     * 使用JSON
     * 使用Bean 序列化
     * */
    @Test
    public void Test01() throws JsonProcessingException {
        User user = new User("luojiaju", "123");

        //对象转成JSON对象
        String jsonUser = new ObjectMapper().writeValueAsString(user);

        //设置String类型 key value
        redisTemplate.opsForValue().set("user",jsonUser);

//        redisTemplate.opsForValue().set("user",user);

        System.out.println(redisTemplate.opsForValue().get("user"));
    }
}
