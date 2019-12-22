package com.example.admin.data.mapper;

import com.example.admin.data.entity.Dictionary;
import com.example.admin.data.vo.DictionaryVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DictionaryMapper extends Mapper<Dictionary> {

    List<Dictionary> query(DictionaryVO dictionaryVO);

    Long queryCount(DictionaryVO dictionaryVO);

}
