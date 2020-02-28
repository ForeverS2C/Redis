package com.slx.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

/**
 * List类型的指令
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test04.class)
public class Test04 {

    @Test
    public void test1() {
        Jedis jedis = new Jedis();
        //jedis.lpush("list1","aaa","bbb","ccc");
        //{'id':1,'name':'苹果手机','price':30.8}
        jedis.lpush("json","{'id':'001','name':'苹果手机','price':30.8}","\"{'id':'002','name':'华为手机','price':30.8}\"");
        String json=jedis.lpop("json");
        log.warn("json={}",json);
    }

}