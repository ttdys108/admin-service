package com.example.admin.data.service;

import com.example.admin.data.entity.Dictionary;
import com.example.admin.data.mapper.DictionaryMapper;
import com.example.admin.data.vo.DictionaryVO;
import com.ttdys108.commons.http.Response;
import com.ttdys108.commons.utils.Pagination;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DictionaryService extends AbstractService<DictionaryMapper, Dictionary> {

    /**
     * 查询所有dict，并按locale分组
     * @return locale map
     */
    public Response<Map<String, Map<String, String>>> queryForMap() {
        Map<String, Map<String, String>> map = new HashMap<>();
        List<Dictionary> dictList = mapper.selectAll();
        dictList.forEach(dict -> {
            map.computeIfAbsent(dict.getLocale(), k -> new HashMap<String, String>()).put(dict.getKey(), dict.getValue());
        });
        return Response.success(map);
    }

    public Response<Pagination<DictionaryVO>> query(DictionaryVO dictionaryVO) {
        List<DictionaryVO> result = new ArrayList<>();
        List<Dictionary> data = mapper.query(dictionaryVO);
        data.forEach(dict -> {
            DictionaryVO vo = new DictionaryVO();
            BeanUtils.copyProperties(dict, vo);
            result.add(vo);
        });
        Long totalCount = mapper.queryCount(dictionaryVO);
        Pagination<DictionaryVO> pagination = Pagination.create(result, totalCount);
        return Response.success(pagination);
    }

}
