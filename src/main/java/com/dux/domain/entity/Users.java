package com.dux.domain.entity;

import lombok.Data;


@Data
public class Users {

    private Long id;
    private String username;
    private String password;
    private String Role;
}