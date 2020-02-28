package com.slx.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * redis集群的使用
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test08.class)
public class Test08 {
    @Test
    public void test1() {
        Set<HostAndPort> nodes = new HashSet<>();
        HostAndPort node1 = new HostAndPort("10.11.68.200", 6379);
        HostAndPort node2 = new HostAndPort("10.11.68.201", 6379);
        HostAndPort node3 = new HostAndPort("10.11.68.202", 6379);
        HostAndPort node4 = new HostAndPort("10.11.68.203", 6379);
        HostAndPort node5 = new HostAndPort("10.11.68.204", 6379);
        HostAndPort node6 = new HostAndPort("10.11.68.205", 6379);
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);
        //创建了redis集群
        JedisCluster cluster = new JedisCluster(nodes);
        //集群的api与单机版的redis的api是一样的!
//        cluster.auth("123");
        cluster.set("node", "集群的使用基本和单机版一模一样!");

        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
