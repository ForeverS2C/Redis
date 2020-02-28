package com.slx.boot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * EnableCaching:开启缓存功能
 */
@Configuration
public class RedisConfig {
    //RedisTemplate:对redis操作的时候,一般使用RedisTemplate模板对象.
    //道理与MySQL的jdbc一样---->JdbcTemplate

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //设置redis值的序列化器,作用就是将存储到redis数据库中的值序列化成指定的格式
        template.setValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    //缓存预热:
    //缓冲穿透:本来应该从redis缓存中根据key取数据,结果根据这个key没有在redis数据库中找到这个key对应的值.
    //此时只能去mysql数据库中进行再次查找,然后再缓存到数据库中.
    //缓存雪崩:当某些原因导致所有的key对应的redis中的值都失效了,此时可能会有大并发量的请求mysql服务器,
    //造成mysql服务器瞬间的压力暴增,可能使得mysql和其他web服务崩溃.该现象就是缓存雪崩.
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        cacheManager.setDefaultExpiration(60);
//        //Map<String,Long> map=new HashMap<>();
//        //是设置只对项目中product这样的key设置的过期时间
//        //map.put("product",5L);
//        //map.put("user",10L);
//        //cacheManager.setExpires(map);
//        return cacheManager;
//    }
}
