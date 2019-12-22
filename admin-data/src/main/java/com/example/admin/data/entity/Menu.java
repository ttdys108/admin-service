package com.example.admin.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "tbl_menu")
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String auth;

    @Column(name = "description")
    private String desc;

    private Long parent;

    private String path;

    private Status status;

    private String icon;

    private Integer priority;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private List<Menu> children;

}
