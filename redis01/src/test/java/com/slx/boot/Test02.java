package com.slx.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * String类型的指令
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test02.class)
public class Test02 {
    @Test
    public void test1() {
        Jedis jedis = new Jedis();
        Set<String> keys = jedis.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            log.warn("key={}", key);
        }
    }

    @Test
    public void test2() {
        Jedis jedis = new Jedis();
        jedis.set("name", "slx");
        jedis.set("age", "20", "nx", "ex", 10);
        String name = jedis.get("name");
        String age = jedis.get("age");
        log.warn("name={}", name + ",age={}", age);
    }

    @Test
    public void test3() {
        Jedis jedis = new Jedis();
        jedis.set("name", "slx");
        jedis.expire("name", 20);
        String name = jedis.get("name");
        log.warn("name={}", name);
    }

    @Test
    public void test4() {
        Jedis jedis = new Jedis();
        jedis.set("age", "20");
        jedis.incrBy("age", 5);
        //jedis.decrBy()
        String age = jedis.get("age");
        log.warn("age={}", age);
    }

    @Test
    public void test5() {
        Jedis jedis = new Jedis();
        jedis.mset("k1", "aaa", "k2", "bbb");
        List<String> mget = jedis.mget("k1", "k2");
        log.warn("size={}", mget.size());
    }

}
