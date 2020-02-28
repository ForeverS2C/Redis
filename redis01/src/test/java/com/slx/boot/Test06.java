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
 * zset类型的指令
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test06.class)
public class Test06 {

    @Test
    public void test1() {
        Jedis jedis = new Jedis();
        jedis.zadd("z1", 1.0, "aaa");
        jedis.zadd("z1", 0.8, "bbb");
        jedis.zadd("z1", 1.1, "ddd");
        jedis.zadd("z1", 1.3, "ccc");

        Set<String> set = jedis.zrangeByScore("z1", 1.0, 1.3);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            log.warn("value={}", value);
        }
    }

}
