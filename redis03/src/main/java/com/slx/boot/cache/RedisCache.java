package com.slx.boot.cache;

import com.slx.boot.utils.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自定义的mybatis二级缓存,利用redis来实现.
 */
public class RedisCache implements Cache {

    private final String id;

    //ReadWriteLock用来实现一些原子性的操作
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private long expireTime=30;

    //@Autowired---->注意:此处不能使用@Autowired注解来注入值.
    private RedisTemplate redisTemplate;

    private RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }

    //必须有一个带id的构造方法
    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("RedisCache类必须有一个id参数!");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if(value!=null){
            redisTemplate=getRedisTemplate();
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key,value,expireTime,TimeUnit.SECONDS);
        }
    }

    @Override
    public Object getObject(Object key) {
        if(key!=null){
            redisTemplate=getRedisTemplate();
            ValueOperations operations = redisTemplate.opsForValue();
            return operations.get(key);
        }
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {
        redisTemplate=getRedisTemplate();
        redisTemplate.execute((RedisCallback) redisConnection -> {
            redisConnection.flushDb();
            return null;
        });
    }

    @Override
    public int getSize() {
        redisTemplate=getRedisTemplate();
        Long size = (Long) redisTemplate.execute((RedisCallback<Long>) redisConnection -> redisConnection.dbSize());
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
