package com.slx.boot.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate template;

    public void setKey(String key, String value) {
        //HashOperations<String, Object, Object> stringObjectObjectHashOperations = template.opsForHash();
        ValueOperations<String, String> operations = template.opsForValue();
        operations.set(key, value);
    }

    public String getKey(String key) {
        ValueOperations<String, String> operations = template.opsForValue();
        return operations.get(key);
    }
}

