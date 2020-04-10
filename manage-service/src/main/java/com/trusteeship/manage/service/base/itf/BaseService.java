package com.trusteeship.manage.service.base.itf;

import com.core.page.BaseParam;
import com.core.page.Page;

public interface BaseService<T,Q extends BaseParam> {
    T insert(T entity);
    T findById(Integer id);
    int deleteById(Integer id);
    int updateById(T entity);
    Page<T> list(Q entity);
}
