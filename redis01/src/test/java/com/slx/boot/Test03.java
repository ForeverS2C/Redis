package com.slx.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

/**
 * Hash类型的指令
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test03.class)
public class Test03 {
    @Test
    public void test1() {
        Jedis jedis = new Jedis();
        jedis.hset("user", "name", "syc");
        jedis.hset("user", "age", "30");
        String name = jedis.hget("user", "name");
        String age = jedis.hget("user", "age");
        log.warn("name={}", name + ",age={}", age);
    }
}
