package com.example.admin.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.data.entity.Dictionary;
import com.example.admin.data.service.DictionaryService;
import com.example.admin.data.vo.DictionaryVO;
import com.ttdys108.commons.http.Response;
import com.ttdys108.commons.utils.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class DictionaryController {

    @Autowired
    DictionaryService dictionaryService;

    @GetMapping("/dict/all")
    public Response<Map<String, Map<String, String>>> queryAll() {
        log.info("request query dicts all");
        return dictionaryService.queryForMap();
    }

    @GetMapping("/dict/query")
    public Response<Pagination<DictionaryVO>> query(DictionaryVO dictionaryVO) {
        log.info("query dictionarys, params:{}", JSONObject.toJSONString(dictionaryVO));

        return dictionaryService.query(dictionaryVO);
    }

}
