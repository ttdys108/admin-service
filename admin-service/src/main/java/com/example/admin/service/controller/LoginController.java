package com.example.admin.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.data.entity.Login;
import com.example.admin.data.service.LoginService;
import com.ttdys108.commons.exception.ServiceException;
import com.ttdys108.commons.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public Response<String> login(@RequestBody Login login) {
        try {
            log.info("login request: {}", JSONObject.toJSONString(login));
            return loginService.login(login);
        } catch (ServiceException e) {
            return Response.error(e);
        }
    }

    /**
     * 用户注册
     * @param login
     * @return
     */
    @PostMapping("/register")
    public Response<String> register(@RequestBody Login login) {
        try {
            log.info("register request: {}", JSONObject.toJSONString(login));
            return loginService.register(login);
        } catch (ServiceException e) {
            return Response.error(e);
        }
    }

}
