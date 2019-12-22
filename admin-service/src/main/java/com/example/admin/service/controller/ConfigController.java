package com.example.admin.service.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.admin.data.entity.Config;
import com.example.admin.data.service.ConfigService;
import com.example.admin.data.vo.ConfigVO;
import com.ttdys108.commons.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ConfigController {

    @Autowired
    ConfigService configService;

    @GetMapping("/config/all")
    public Response<List<Config>> queryAll() {
        log.info("request all configs ");

        return configService.queryAll();
    }

    @GetMapping("/config/query")
    public Response<List<Config>> query(ConfigVO configVO) {
        log.info("request config, params:{}", JSONObject.toJSONString(configVO));
        return configService.query(configVO);
    }

}
