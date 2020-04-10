package com.trusteeship.manage.service.service.itf;

import com.trusteeship.manage.service.base.itf.BaseService;
import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.bean.page.TDatabaseP;

import java.util.List;


public interface TDatabaseService extends BaseService<TDatabase, TDatabaseP> {

    TDatabase selectByUsername(String userName);

    List<String> getDatabaseIPList(String databaseIp);
}




