package com.example.admin.data.service;

import com.example.admin.data.entity.Config;
import com.example.admin.data.entity.ConfigType;
import com.example.admin.data.entity.Dictionary;
import com.example.admin.data.mapper.ConfigMapper;
import com.example.admin.data.mapper.DictionaryMapper;
import com.example.admin.data.vo.DictionaryVO;
import com.ttdys108.commons.exception.ErrorCode;
import com.ttdys108.commons.exception.ServiceException;
import com.ttdys108.commons.http.Response;
import com.ttdys108.commons.utils.CollectionUtils;
import com.ttdys108.commons.utils.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DictionaryService extends AbstractService<DictionaryMapper, Dictionary> {

    @Autowired
    ConfigMapper configMapper;

    /**
     * 查询所有dict，并按locale分组
     * @return locale map
     */
    public Response<Map<String, Map<String, String>>> queryForMap() {
        Map<String, Map<String, String>> map = new HashMap<>();
        List<Dictionary> dictList = mapper.selectAll();
        dictList.forEach(dict -> {
            map.computeIfAbsent(dict.getLocale(), k -> new HashMap<String, String>()).put(dict.getKey(), dict.getValue());
        });
        return Response.success(map);
    }

    public Response<Pagination<DictionaryVO>> query(DictionaryVO dictionaryVO) {
        List<DictionaryVO> result = new ArrayList<>();
        List<Dictionary> data = mapper.query(dictionaryVO);
        data.forEach(dict -> {
            DictionaryVO vo = new DictionaryVO();
            BeanUtils.copyProperties(dict, vo);
            result.add(vo);
        });
        Long totalCount = mapper.queryCount(dictionaryVO);
        Pagination<DictionaryVO> pagination = Pagination.create(result, totalCount);
        return Response.success(pagination);
    }


    public Response<Void> add(DictionaryVO dictionaryVO) throws ServiceException {
        checkAdd(dictionaryVO);
        List<Dictionary> dicts = transform(dictionaryVO);
        mapper.batchInsert(dicts);
        return Response.success(null);
    }

    private void checkAdd(DictionaryVO dictionaryVO) throws ServiceException {
        if(StringUtils.isAnyEmpty(dictionaryVO.getDesc(), dictionaryVO.getKey()))
            throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
        Dictionary dict = new Dictionary();
        dict.setKey(dictionaryVO.getKey());
        List<Dictionary> exists = mapper.select(dict);
        if(!exists.isEmpty())
            throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
        checkLocaleValueMap(dictionaryVO.getLocaleValueMap());
    }

    /**
     * 更新页面查询dict信息
     * @param key key
     * @return dictVO
     */
    public Response<DictionaryVO> queryForUpdate(String key) throws ServiceException {
        Dictionary dict = new Dictionary();
        dict.setKey(key);
        List<Dictionary> res = mapper.select(dict);
        if(res.isEmpty())
            throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
        DictionaryVO vo = new DictionaryVO();
        BeanUtils.copyProperties(res.get(0), vo);
        Map<String, String> localeValueMap = new HashMap<>();
        vo.setLocaleValueMap(localeValueMap);
        res.forEach(d -> {
            localeValueMap.put(d.getLocale(), d.getValue());
        });

        return Response.success(vo);
    }

    public Response<Void> batchUpdate(DictionaryVO dictionaryVO) throws ServiceException {
        checkUpdate(dictionaryVO);
        List<Dictionary> dicts = transform(dictionaryVO);
        mapper.batchUpdate(dicts);
        return Response.success(null);
    }

    // DictionaryVO转换为List<Dictionary>
    private List<Dictionary> transform(DictionaryVO dictionaryVO) {
        List<Dictionary> dicts = new ArrayList<>();
        dictionaryVO.getLocaleValueMap().forEach((locale, val) -> {
            Dictionary dict = new Dictionary();
            BeanUtils.copyProperties(dictionaryVO, dict);
            dict.setLocale(locale);
            dict.setValue(val);
            dicts.add(dict);
        });
        return dicts;
    }

    private void checkUpdate(DictionaryVO vo) throws ServiceException {
        if(StringUtils.isAnyEmpty(vo.getKey(), vo.getDesc()))
            throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
        Dictionary dict = new Dictionary();
        dict.setKey(vo.getKey());
        List<Dictionary> exists = mapper.select(dict);
        if(exists.isEmpty())
            throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);

        checkLocaleValueMap(vo.getLocaleValueMap());

    }

    //校验前端传的LocaleValueMap
    private void checkLocaleValueMap(Map<String, String> localeValueMap) throws ServiceException {
        if(localeValueMap == null)
            throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
        Config cfg = new Config();
        cfg.setType(ConfigType.lang);
        List<Config> locales = configMapper.select(cfg);
        if(locales.isEmpty())
            return;
        List<String> supportLocales = locales.stream().map(Config::getKey).collect(Collectors.toList());
        if(localeValueMap.size() < supportLocales.size())
            throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
        for (Map.Entry<String, String> entry : localeValueMap.entrySet()) {
            if (StringUtils.isBlank(entry.getValue()) || !supportLocales.contains(entry.getKey()))
                throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT);
        }
    }


}
