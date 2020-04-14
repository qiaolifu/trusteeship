package com.trusteeship.manage.service.service;

import org.springframework.stereotype.Service;
import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.bean.page.TDatabaseP;
import com.trusteeship.manage.service.dao.TDatabaseDao;
import com.trusteeship.manage.service.service.itf.TDatabaseService;
import com.trusteeship.manage.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tDatabaseService")
public class TDatabaseServiceImpl extends BaseServiceImpl<TDatabase, TDatabaseP> implements TDatabaseService {

    @Autowired
    private TDatabaseDao tDatabaseDao;

    @Override
    public TDatabase selectByUsername(String userName) {
        return tDatabaseDao.selectByUsername(userName);
    }

    @Override
    public List<String> getDatabaseIPList(String databaseIp) {
        return tDatabaseDao.getDatabaseIPList(databaseIp);
    }

    @Override
    public List<TDatabase> all() {
        return tDatabaseDao.getAll();
    }
}