package com.slx.boot.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private int id;

    private String username;

    private String password;

}
