package com.example.admin.data.entity;

import com.sun.xml.internal.ws.spi.db.DatabindingException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Table(name = "tbl_config")
@Entity
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cfg_key")
    private String key;

    @Column(name = "cfg_value")
    private String value;

    private ConfigType type;

    @Column(name = "sub_type")
    private ConfigType subType;

    private Status status;

    @Column(name = "description")
    private String desc;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
