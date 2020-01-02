package com.example.admin.data.service;

import com.example.admin.data.entity.User;
import com.example.admin.data.mapper.UserMapper;
import com.example.admin.data.vo.UserVO;
import com.ttdys108.commons.http.Response;
import com.ttdys108.commons.utils.Pagination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractService<UserMapper, User> {

    public Response<Pagination<User>> query(UserVO userVO) {
        List<User> res = mapper.query(userVO);
        Long count = mapper.queryCount(userVO);
        Pagination<User> pagination = Pagination.create(res, count);
        return Response.success(pagination);

    }

}
