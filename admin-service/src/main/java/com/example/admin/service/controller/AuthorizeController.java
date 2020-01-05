package com.example.admin.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.data.entity.Authorize;
import com.example.admin.data.entity.Menu;
import com.example.admin.data.service.AuthorizeService;
import com.example.admin.data.vo.AuthorizeVO;
import com.example.admin.data.vo.MenuVO;
import com.ttdys108.commons.exception.ServiceException;
import com.ttdys108.commons.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class AuthorizeController {

    @Autowired
    AuthorizeService authorizeService;

    @GetMapping("/authr/query")
    public Response<List<Authorize>> queryAuthors(Long userId) {
        return authorizeService.queryAuthorizes(userId);
    }

    @PostMapping("/authr/grant")
    public Response<Void> grantAuthors(@RequestBody AuthorizeVO authrVO) {
        try {
            log.info("grant authr request params:{}", JSONObject.toJSONString(authrVO));
            return authorizeService.grant(authrVO);
        } catch (ServiceException e) {
            return Response.error(e);
        }
    }




}
