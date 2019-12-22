package com.example.admin.data.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DictionaryVO extends AbstractVO {

    private Long id;

    private String key;

    private String value;

    private String locale;

    private String desc;

    private Date createTime;

    private Date updateTime;

    private String query;

    /**
     * locale和value对应的map
     */
    private Map<String, String> localeValueMap;

}
