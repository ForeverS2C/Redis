package com.slx.boot.web;

import com.github.pagehelper.PageInfo;
import com.slx.boot.domain.User;
import com.slx.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //引入redis的方案一:
    //RedisTemplate redisTemplate.get(key)

    //@RequestMapping(value = "/users",method = RequestMethod.GET)
    @GetMapping("/users")
    List<User> users() {
        //方案一:先去redis数据库中查询用户信息,判断有没有?--->redisTemplate.get(key)
        //如果redis数据库中,直接用redis中的数据;
        //没有:去查询mysql数据库,然后存储到redis数据库.--->redisTemplate.set(key,value)

        //方案二:利用mybatis的二级缓存功能,把mybatis的二级缓存用redis来替代.
        return userService.findAll();
    }

    //接收参数的注解:
    //@RequestParam
    //@RequestBody
    //@PathVariable

    @GetMapping("/pages")
    PageInfo<User> pages(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return userService.findAllByPage(pageNo, pageSize);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User user){
        User oldUser = userService.fintById(id);
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        userService.updateUser(oldUser);
        return oldUser;
    }

}
