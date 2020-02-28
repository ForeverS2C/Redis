package com.slx.boot;

import com.slx.boot.mapper.RedisDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
//@SpringBootTest(classes = RedisTest.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisDao redisDao;

    @Test
    public void test1() {
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set("test", "第一个模板...", 5000, TimeUnit.SECONDS);
        Object test = operations.get("test");
        log.warn("test={}", test);
    }

    @Test
    public void test2() {
        redisDao.setKey("name", "yyyy");
        redisDao.setKey("age", "30");
        String name = redisDao.getKey("name");
        String age = redisDao.getKey("age");
        log.warn("name={}", name);
        log.warn("age={}", age);
    }
}