package com.slx.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

/**
 * set类型的指令
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test05.class)
public class Test05 {

    @Test
    public void test1() {
        //单机版的redis对象
        Jedis jedis = new Jedis();
        jedis.sadd("s1", "aaa", "bbb", "ccc");
        Set<String> set = jedis.smembers("s1");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            log.warn("value={}", value);
        }
    }

}
