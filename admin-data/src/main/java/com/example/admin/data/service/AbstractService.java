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

    public Response<List<T>> query(T t) {
        List<T> res = mapper.select(t);
        return Response.success(res);
    }

    public Response<List<T>> queryAll() {
        List<T> result = mapper.selectAll();
        return Response.success(result);
    }

    public Response<List<T>> queryByExample(Example example) {
        List<T> result = mapper.selectByExample(example);
        return Response.success(result);
    }

    public Response<Integer> delete(T t) {
        Integer res = mapper.delete(t);
        return Response.success(res);
    }

    public Response<Integer> deleteByPrimaryKey(Object key) {
        Integer res = mapper.deleteByPrimaryKey(key);
        return Response.success(res);
    }

    public Response<Integer> create(T t) {
        Integer res = mapper.insertSelective(t);
        return Response.success(res);
    }

}
