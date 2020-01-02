package com.example.admin.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.data.entity.User;
import com.example.admin.data.service.UserService;
import com.example.admin.data.vo.UserVO;
import com.ttdys108.commons.http.Response;
import com.ttdys108.commons.utils.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/query")
    public Response<Pagination<User>> query(UserVO userVO) {
        log.info("query user params: {}", JSONObject.toJSONString(userVO));

        return userService.query(userVO);
    }

    @PostMapping("/user/update")
    public Response<Void> update(@RequestBody User user) {
        userService.update(user);
        return Response.success(null);
    }


}
