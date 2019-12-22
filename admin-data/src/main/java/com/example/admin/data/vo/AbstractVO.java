package com.example.admin.data.vo;

import lombok.Getter;
import lombok.Setter;

//提供全部getter方法mybatis才会调用getPageOffset?
@Getter
@Setter
public abstract class AbstractVO {

    protected Integer pageSize;

    protected Integer pageNo;

    protected Integer getPageOffset() {
        return pageNo == null ? null : (this.pageNo - 1) * pageSize;
    }

}
