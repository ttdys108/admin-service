package com.example.admin.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.data.entity.Login;
import com.example.admin.data.service.LoginService;
import com.ttdys108.commons.exception.ErrorCode;
import com.ttdys108.commons.exception.ServiceException;
import com.ttdys108.commons.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public Response<String> login(@RequestBody Login login, HttpSession session) {
        try {
            checkVCode(login, session);
            log.info("login request: {}", JSONObject.toJSONString(login));
            return loginService.login(login);
        } catch (ServiceException e) {
            return Response.error(e);
        }
    }

    private void checkVCode(Login login, HttpSession session) throws ServiceException {
        if(VerifyCodeController.DISTRIBUTED_CODE == null || !VerifyCodeController.DISTRIBUTED_CODE.equalsIgnoreCase(login.getVcode()))
            throw new ServiceException("111000", "verify code error");
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
