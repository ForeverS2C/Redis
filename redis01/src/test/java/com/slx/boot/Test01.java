package com.slx.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test01.class)
public class Test01 {

    @Test
    public void test1() {
        //单机版的redis对象
        Jedis jedis = new Jedis();
        //Jedis jedis=new Jedis("127.0.0.1",6379);
        //设置密码
//        jedis.auth("");
        String ping = jedis.ping();
        log.warn("ping的结果=" + ping);
    }
}
