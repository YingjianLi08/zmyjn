package com.zmyjn.core.base.service;

import java.util.List;
import java.util.Map;


/**
 * @ClassName: BaseService
 * @描述: 基础Service
 * @author liyj
 */
public interface BaseService<T, PK> {

    int insert(T entiry);

    int deleteById(PK id);

    int deleteByIds(PK[] ids);

    int update(T paramT);

    T findById(PK id);

    T findOneByMap(Map<String, Object> paramMap);

    List<T> findListByMap(Map<String, Object> paramMap);


}
