package com.example.admin.data.service;

import com.example.admin.data.entity.Authorize;
import com.example.admin.data.entity.GrantTypes;
import com.example.admin.data.mapper.AuthorizeMapper;
import com.example.admin.data.vo.AuthorizeVO;
import com.ttdys108.commons.exception.ErrorCode;
import com.ttdys108.commons.exception.ServiceException;
import com.ttdys108.commons.http.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizeService extends AbstractService<AuthorizeMapper, Authorize> {

    public Response<List<Authorize>> queryAuthorizes(Long userId) {
        Authorize authr = new Authorize();
        authr.setUserId(userId);
        List<Authorize> authrList = mapper.select(authr);
        return Response.success(authrList);
    }

//    private void addAuthrInfo(List<MenuVO> menuList, List<Authorize> authrList) {
//        menuList.forEach(menu -> {
//            for(Authorize authr : authrList) {
//                if(authr.getResource().equals(menu.getPath())) {
//                    menu.setGrants(authr.getGrants());
//                    authrList.remove(authr);
//                    break;
//                }
//            }
//            if(menu.getChildren() != null)
//                addAuthrInfo(menu.getChildren(), authrList);
//        });
//    }


    public Response<Void> grant(AuthorizeVO authrVO) throws ServiceException {
        checkGrant(authrVO);
        Authorize authorize = new Authorize();
        authorize.setUserId(authrVO.getUserId());
        mapper.delete(authorize);
        List<Authorize> authrList = transform(authrVO);
        mapper.batchInsert(authrList);
        return Response.success(null);
    }

    private void checkGrant(AuthorizeVO authrVO) throws ServiceException {
        if(authrVO.getUserId() == null)
            throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
    }

    private List<Authorize> transform(AuthorizeVO authrVO) {
        List<Authorize> authrList = new ArrayList<>();
        authrVO.getAuthrInfos().forEach((resource, authrInfo) -> {
            Authorize authr = new Authorize();
            authr.setUserId(authrVO.getUserId());
            authr.setResource(resource);
            authr.setGrants(authrInfo.calculateGrants());
            authrList.add(authr);
        });
        return authrList;
    }


}
