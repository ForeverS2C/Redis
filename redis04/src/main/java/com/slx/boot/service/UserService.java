package com.slx.boot.service;

import com.slx.boot.domain.User;
import com.slx.boot.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserService {

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Resource
    private UserMapper userMapper;

    public void addUser(User user) {
        log.warn("正在执行添加，添加一个用户...");
        userMapper.insert(user);
    }

    public void deleteUserById(int id) {
        userMapper.deleteById(id);
        String key = "user_"+id;
        if(redisTemplate.hasKey(key)){
            log.warn("正在执行删除，删除redis中对应key...");
            redisTemplate.delete(key);
        }
    }


    public void updateUser(User user) {
        userMapper.updateById(user);
        String key = "user_"+ user.getId();
        if(redisTemplate.hasKey(key)){
            log.warn("正在执行修改，删除redis中对应key...");
            redisTemplate.delete(key);
        }
    }

    public User getUserById(Integer id) {
        String key = "user_"+id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        if(redisTemplate.hasKey(key)){
            User user = operations.get(key);
            log.warn("正在执行查询，从redis中查找...");
            return user;
        }
        
        log.warn("正在执行查询，从mysql中查找...");
        User user = (User) userMapper.selectById(id);

        operations.set(key,user,10, TimeUnit.MINUTES);
        return user;
    }
}
