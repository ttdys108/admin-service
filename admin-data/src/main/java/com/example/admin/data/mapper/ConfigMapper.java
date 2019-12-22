package com.example.admin.data.mapper;

import com.example.admin.data.entity.Config;
import com.example.admin.data.vo.ConfigVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ConfigMapper extends Mapper<Config> {

    List<Config> query(ConfigVO configVO);

    Long queryCount(ConfigVO configVO);
}
