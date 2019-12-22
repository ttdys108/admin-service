package com.example.admin.data.service;

import com.example.admin.data.entity.Config;
import com.example.admin.data.mapper.ConfigMapper;
import com.example.admin.data.vo.ConfigVO;
import com.ttdys108.commons.http.Response;
import com.ttdys108.commons.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigService extends AbstractService<ConfigMapper, Config> {

    public Response<List<Config>> query(ConfigVO configVO) {
        List<Config> result = mapper.query(configVO);
        return Response.success(result);
    }

    public Response<Pagination<Config>> queryPaging(ConfigVO configVO){
        List<Config> result = mapper.query(configVO);
        Long totalCount = mapper.queryCount(configVO);
        return Response.success(Pagination.create(result, totalCount));
    }


}
