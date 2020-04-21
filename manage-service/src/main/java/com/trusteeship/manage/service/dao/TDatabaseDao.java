package com.trusteeship.manage.service.dao;

import com.trusteeship.manage.service.base.itf.BaseDao;
import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.bean.entity.TUser;
import com.trusteeship.manage.service.bean.page.TDatabaseP;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author Jo
 * @email 124185954@qq.com
 * @date 2020-03-30 17:08:39
 */
@Repository
public interface TDatabaseDao extends BaseDao<TDatabase,TDatabaseP> {

    TDatabase selectByUsername(String userName);

    List<String> getDatabaseIPList(String databaseIp);

    List<TDatabase> getAll();

    List<String> dbList(TUser user);
}
