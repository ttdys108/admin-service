package com.example.admin.data.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {

    private Long id;

    private LoginType type;

    private String username;

    private String password;

    private String mobile;

    private String vcode;

    private Boolean rememberMe;

}
