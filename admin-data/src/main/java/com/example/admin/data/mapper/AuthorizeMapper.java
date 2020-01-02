package com.example.admin.data.mapper;

import com.example.admin.data.entity.Authorize;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuthorizeMapper extends Mapper<Authorize> {

    void batchInsert(List<Authorize> list);
}
