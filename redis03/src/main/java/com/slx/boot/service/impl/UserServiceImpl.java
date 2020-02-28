package com.slx.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.slx.boot.domain.User;
import com.slx.boot.mapper.UserMapper;
import com.slx.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {

        return userMapper.findAll();
    }

    @Override
    public PageInfo<User> findAllByPage(int pageNo,int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<User> list = userMapper.findAllByPage();
        return new PageInfo<>(list);
    }

    @Override
    public User fintById(int id) {

        return userMapper.findbyId(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
