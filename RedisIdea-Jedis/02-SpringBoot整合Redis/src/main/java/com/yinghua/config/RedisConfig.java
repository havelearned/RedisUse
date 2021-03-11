package com.yinghua.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author admin
 */
@Configuration
public class RedisConfig  {


    /**
     * 编写自定义的Redis模板(RedisTemplate)
     * 这个一个固定的模板,直接使用
     * */
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> ort = new RedisTemplate<>();
        ort.setConnectionFactory(redisConnectionFactory);
        /**
         * 配置具体的序列化方式:
         *  ByteArrayRedisSerializer
         *  GenericJackson2JsonRedisSerializer
         *  GenericToStringSerializer
         *  Jackson2JsonRedisSerializer
         *  JdkSerializationRedisSerializer
         *  OxmSerializer
         *  StringRedisSerializer
         * */
        //序列化配置
        Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        objectJackson2JsonRedisSerializer.setObjectMapper(objectMapper);


        //String 的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //key采用String的序列化方式
        ort.setKeySerializer(stringRedisSerializer);

        //hash的key使用String序列化
        ort.setHashKeySerializer(stringRedisSerializer);

        //value 序列化方式使用jackson
        ort.setValueSerializer(objectJackson2JsonRedisSerializer);

        //hash也采用 value序列化方式采用jacks
        ort.setHashKeySerializer(objectJackson2JsonRedisSerializer);

        //全部设置到位
        ort.afterPropertiesSet();


        return ort;

    }

}
