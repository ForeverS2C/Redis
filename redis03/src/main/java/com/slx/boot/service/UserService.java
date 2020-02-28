package com.slx.boot.service;


import com.github.pagehelper.PageInfo;
import com.slx.boot.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    PageInfo<User> findAllByPage(int pageNo, int pageSize);

    User fintById(int id);

    void updateUser(User user);
}
