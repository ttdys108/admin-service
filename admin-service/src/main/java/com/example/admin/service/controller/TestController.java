package com.example.admin.service.controller;

import com.example.admin.data.vo.ConfigVO;
import com.ttdys108.commons.http.Response;
import com.ttdys108.commons.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    RedisTemplate<String, Response<ConfigVO>> redisTemplate;

    String redisConfigKey = "test_config";

    @GetMapping("/redis/set")
    public String setRedis() {
        ConfigVO configVO = new ConfigVO();
        configVO.setId(1L);
        Response<ConfigVO> res = Response.success(configVO);
        redisTemplate.opsForValue().set(redisConfigKey, res);
        return "ok";
    }

    @GetMapping("/redis/get")
    public Response<ConfigVO> getRedis() {
        return (Response<ConfigVO>) redisTemplate.opsForValue().get(redisConfigKey);
    }


}
