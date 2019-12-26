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
    @Column
    private Long id;

    @Column
    private String code;

    @Column
    private String auth;

    @Column(name = "description")
    private String desc;

    @Column
    private Long parent;

    @Column
    private String path;

    @Column
    private Status status;

    @Column
    private String icon;

    @Column
    private Integer priority;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private List<Menu> children;

}
