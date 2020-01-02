package com.example.admin.data.vo;

import com.example.admin.data.entity.UserStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class UserVO extends AbstractVO {

    private Long id;

    private String username;

    private String password;

    private String account;

    private String avatar;

    private String nickname;

    private String mobile;

    private String email;

    private UserStatus status;

    private String deviceId;

    private Date createTime;

    private Date updateTime;


}
