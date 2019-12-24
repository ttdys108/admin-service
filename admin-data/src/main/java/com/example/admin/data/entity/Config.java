package com.example.admin.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

//@Column不能省略，tkmapper select方法需要
@Getter
@Setter
@Table(name = "tbl_config")
@Entity
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "cfg_key")
    private String key;

    @Column(name = "cfg_value")
    private String value;

    @Column
    private ConfigType type;

    @Column(name = "sub_type")
    private ConfigType subType;

    @Column
    private Status status;

    @Column(name = "description")
    private String desc;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
