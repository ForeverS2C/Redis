package com.slx.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis连接池的使用
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test07.class)
public class Test07 {

    @Test
    public void test1() {
        //得到了一个redis的连接池对象.
        JedisPool pool=new JedisPool("127.0.0.1",6379);
        Jedis jedis = pool.getResource();
//        jedis.auth("");
        jedis.set("name","slx");
        String name=jedis.get("name");
        log.warn("name={}",name);

        jedis.close();
        pool.close();
        pool.destroy();
    }

}
