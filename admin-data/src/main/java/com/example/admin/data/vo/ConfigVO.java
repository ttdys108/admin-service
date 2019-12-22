package com.example.admin.data.vo;

import com.example.admin.data.entity.ConfigType;
import com.example.admin.data.entity.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class ConfigVO extends AbstractVO {

    private Long id;

    private String key;

    private String value;

    private ConfigType type;

    private ConfigType subType;

    private Status status;

    private String desc;

    private Date createTime;

    private Date updateTime;

}
