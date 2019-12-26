package com.example.admin.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.data.entity.Menu;
import com.example.admin.data.entity.Status;
import com.example.admin.data.service.MenuService;
import com.example.admin.data.vo.MenuVO;
import com.ttdys108.commons.exception.ServiceException;
import com.ttdys108.commons.http.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menu/all")
    public Response<List<Menu>> queryAll() {

        return menuService.queryAll();
    }

    @GetMapping("/menu/status")
    public Response<List<Status>> queryMenuStatus() {
        List<Status> res = Arrays.asList(Status.values());
        return Response.success(res);
    }

    @PostMapping("/menu/add")
    public Response<Void> add(@RequestBody Menu menu) {
        try {
            log.info("add menu request, params:{}", JSONObject.toJSONString(menu));
            return menuService.addMenu(menu);
        } catch (ServiceException e) {
            return Response.error(e);
        }
    }

    @PostMapping("/menu/update")
    public Response<Void> update(@RequestBody Menu menu) {
        try {
            log.info("update menu request, params: {}", JSONObject.toJSONString(menu));
            return menuService.updateMenu(menu);
        } catch (ServiceException e) {
            return Response.error(e);
        }
    }

    @PostMapping("/menu/delete")
    public Response<Integer> delete(Long id) {
        return menuService.deleteByPrimaryKey(id);
    }

}
