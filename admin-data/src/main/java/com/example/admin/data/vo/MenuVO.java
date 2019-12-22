package com.example.admin.data.vo;

import com.example.admin.data.entity.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MenuVO {

    private Long id;

    private String code;

    private String auth;

    private String desc;

    private Long parent;

    private String path;

    private Status status;

    private String icon;

    private Integer priority;

    private Date createTime;

    private Date updateTime;

    private List<MenuVO> children;

}
