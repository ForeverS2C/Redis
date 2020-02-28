package com.slx.boot.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@TableName(value = "user")
public class User extends Model<User> {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
