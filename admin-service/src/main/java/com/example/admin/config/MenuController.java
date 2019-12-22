package com.example.admin.config;

import com.example.admin.data.entity.Menu;
import com.example.admin.data.service.MenuService;
import com.example.admin.data.vo.MenuVO;
import com.ttdys108.commons.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menu/all")
    public Response<List<Menu>> queryAll() {

        return menuService.queryAll();
    }


}
