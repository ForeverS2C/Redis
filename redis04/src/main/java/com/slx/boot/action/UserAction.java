package com.slx.boot.action;

import com.slx.boot.domain.User;
import com.slx.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAction {

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUserById(Integer.parseInt(id));
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id) {
        return userService.getUserById(Integer.valueOf(id));
    }

}
