package com.example.admin.data.mapper;

import com.example.admin.data.entity.User;
import com.example.admin.data.vo.UserVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    List<User> query(UserVO userVO);

    Long queryCount(UserVO userVO);
}
