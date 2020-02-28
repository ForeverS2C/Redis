package com.slx.boot.mapper;

import com.slx.boot.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

    List<User> findAllByPage();

    User findbyId(@Param("id") int id);

    void updateUser(User user);
}
