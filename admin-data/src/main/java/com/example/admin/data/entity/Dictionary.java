package com.example.admin.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "tbl_dict")
@Entity
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dict_key")
    private String key;

    @Column(name = "dict_value")
    private String value;

    @Column(name = "lang")
    private String locale;

    @Column(name = "description")
    private String desc;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;


}
