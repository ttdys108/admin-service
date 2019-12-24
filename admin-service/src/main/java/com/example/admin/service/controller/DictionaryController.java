package com.example.admin.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.data.entity.Dictionary;
import com.example.admin.data.service.DictionaryService;
import com.example.admin.data.vo.DictionaryVO;
import com.ttdys108.commons.exception.ServiceException;
import com.ttdys108.commons.http.Response;
import com.ttdys108.commons.utils.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/dict/add")
    public Response<Void> add(@RequestBody DictionaryVO dictionaryVO) {
        try {
            log.info("add dict, params:{}", JSONObject.toJSONString(dictionaryVO));
            return dictionaryService.add(dictionaryVO);
        } catch (ServiceException e) {
            return Response.error(e);
        }
    }

    @PostMapping("/dict/delete")
    public Response<Integer> delete(String key) {
        log.info("delete dict, key:{}", key);
        Dictionary dict = new Dictionary();
        dict.setKey(key);
        return dictionaryService.delete(dict);
    }

    @GetMapping("/dict/queryForUpdate")
    public Response<DictionaryVO> queryForUpdate(String key) {
        try {
            log.info("query for update, key:{}", key);
            return dictionaryService.queryForUpdate(key);
        } catch (ServiceException e) {
            return Response.error(e);
        }
    }

    @PostMapping("/dict/update")
    public Response<Void> update(@RequestBody DictionaryVO dictionaryVO) {
        try {
            log.info("update dict: {}", JSONObject.toJSONString(dictionaryVO));
            return dictionaryService.batchUpdate(dictionaryVO);
        } catch (ServiceException e) {
            return Response.error();
        }
    }

}
