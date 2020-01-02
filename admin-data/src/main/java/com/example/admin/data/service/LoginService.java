package com.example.admin.data.service;

import com.example.admin.data.entity.Login;
import com.example.admin.data.entity.LoginType;
import com.example.admin.data.entity.User;
import com.example.admin.data.entity.UserStatus;
import com.example.admin.data.mapper.UserMapper;
import com.ttdys108.commons.exception.ErrorCode;
import com.ttdys108.commons.exception.ServiceException;
import com.ttdys108.commons.http.Response;
import com.ttdys108.commons.utils.CollectionUtils;
import com.ttdys108.commons.utils.JWTUtils;
import com.ttdys108.commons.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;

    @Value("${redis.key.login-token}")
    private String loginTokenKey;

    @Value("${encrypt.secret}")
    private String secret;

    @Value("${login.token.expires}")
    private Integer tokenExpires;

    public Response<String> login(Login login) throws ServiceException {
        checkLogin(login);

        if(login.getType() == LoginType.USERNAME) {
            User example = new User();
            example.setUsername(login.getUsername());
            User exists = userMapper.selectOne(example);
            if(exists == null || !MD5Utils.verify(login.getPassword(), exists.getPassword(), secret)) {
                return Response.error(ErrorCode.LOGIN_VERIFY_FAIL);
            } else if(exists.getStatus() == UserStatus.FROZEN || exists.getStatus() == UserStatus.DELETED) {
                return Response.error(ErrorCode.ILLEGAL_STATUS);
            } else { //登录成功，生成jwt token
                Map<String, String> params = CollectionUtils.ofMap("userId", exists.getId().toString());
                Integer expires = (Boolean.TRUE.equals(login.getRememberMe()) ? null : tokenExpires);
                String token = JWTUtils.generate(secret, expires, params);
                log.info("user:{} login, generate token:{}", login.getUsername(), token);
                return Response.success(token);
            }
        }

        return null;
    }

    /**
     * 检查登录参数
     * @param login login params
     * @throws ServiceException
     */
    private void checkLogin(Login login) throws ServiceException {
        if(login.getType() == null)
            throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
        if(login.getType() == LoginType.USERNAME &&
                (StringUtils.isEmpty(login.getUsername()) || StringUtils.isEmpty(login.getPassword())))
            throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
    }

    public Response<String> register(Login login) throws ServiceException {
        checkRegister(login);
        if(login.getType() == LoginType.USERNAME) {
            User user = new User();
            user.setUsername(login.getUsername());
            String encryptPwd = MD5Utils.encode(login.getPassword(), secret);
            user.setPassword(encryptPwd);
            userMapper.insertSelective(user);
            Map<String, String> params = CollectionUtils.ofMap("userId", user.getId().toString());
            String token = JWTUtils.generate(secret, tokenExpires, params);
            return Response.success(token);
        }

        return null;
    }

    private void checkRegister(Login login) throws ServiceException {
        if(login.getType() == null)
            throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
        if(login.getType() == LoginType.USERNAME) {
            if(StringUtils.isEmpty(login.getUsername()) || StringUtils.isEmpty(login.getPassword()))
                throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
            User user = new User();
            user.setUsername(login.getUsername());
            User exists = userMapper.selectOne(user);
            if(exists != null)
                throw new ServiceException(ErrorCode.REGISTER_USER_EXISTS);
        }

    }

}
