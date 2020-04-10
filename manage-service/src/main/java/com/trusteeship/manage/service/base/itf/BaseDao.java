package com.trusteeship.manage.service.base.itf;

import com.core.page.BaseParam;

import java.util.List;

public interface BaseDao<T,Q extends BaseParam> {
    int insert(T entity);
    T findById(Integer id);
    int deleteById(Integer id);
    int updateById(T entity);
    int listCount(Q entity);
    List<T> list(Q entity);
}
