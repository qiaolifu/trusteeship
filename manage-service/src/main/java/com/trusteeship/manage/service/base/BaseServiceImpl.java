package com.trusteeship.manage.service.base;

import com.core.page.Page;
import com.trusteeship.manage.service.base.itf.BaseDao;
import com.trusteeship.manage.service.base.itf.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl<T,Q extends Page> implements BaseService<T,Q>{

    @Autowired
    protected BaseDao<T,Q> baseDao;//注意需要有泛型 否则自动注入的时候会报错，不知道注入哪个bean

    public T insert(T entity){
        baseDao.insert(entity);
        return entity;
    }
    public T findById(Integer id){
        return (T)baseDao.findById(id);
    }
    public int deleteById(Integer id){
        return baseDao.deleteById(id);
    }
    public int updateById(T entity){
        return (int)baseDao.updateById(entity);
    }
    public Page<T> list(Q page){
        int totalCount = baseDao.listCount(page);
        page.set_totalCount(totalCount);
        page.set_records(baseDao.list(page));
        return page;
    }

}
