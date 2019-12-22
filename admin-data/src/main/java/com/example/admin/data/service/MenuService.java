package com.example.admin.data.service;

import com.example.admin.data.entity.Menu;
import com.example.admin.data.mapper.MenuMapper;
import com.ttdys108.commons.http.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService extends AbstractService<MenuMapper, Menu> {

    /**
     * 返回顶层menu树
     * @return menu
     */
    public Response<List<Menu>> queryAll() {
        List<Menu> result = new ArrayList<>();
        List<Menu> menus = mapper.selectAll();
        menus.forEach(menu -> {
            if(menu.getParent() == null) {
                result.add(menu); //result只返回顶层menulist
            } else {
                for(Menu m : menus) {
                    if(m.getId().equals(menu.getParent())) {
                        if(m.getChildren() == null)
                            m.setChildren(new ArrayList<>());
                        m.getChildren().add(menu);
                        break;
                    }
                }
            }

        });
        return Response.success(result);
    }

}
