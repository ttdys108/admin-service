package com.example.admin.data.service;

import com.ttdys108.commons.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public abstract class AbstractService<M extends Mapper<T>, T> {

    @Autowired
    protected M mapper;

    public Response<T> queryByPrimaryKey(Object key) {
        T result = mapper.selectByPrimaryKey(key);
        return Response.success(result);
    }

    public Response<List<T>> queryAll() {
        List<T> result = mapper.selectAll();
        return Response.success(result);
    }

    public Response<List<T>> queryByExample(Example example) {
        List<T> result = mapper.selectByExample(example);
        return Response.success(result);
    }

}
