package com.trusteeship.manage.service.service;

import com.core.utils.DateUtil;
import com.trusteeship.manage.service.base.AdminConfig;
import com.trusteeship.manage.service.base.BaseServiceImpl;
import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.bean.entity.TUser;
import com.trusteeship.manage.service.bean.page.TUserP;
import com.trusteeship.manage.service.dao.TUserDao;
import com.trusteeship.manage.service.service.itf.TDatabaseService;
import com.trusteeship.manage.service.service.itf.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("tUserService")
public class TUserServiceImpl extends BaseServiceImpl<TUser, TUserP> implements TUserService {

    @Autowired
    private TUserDao tUserDao;
    @Autowired
    private TDatabaseService tDatabaseService;
    @Autowired
    private AdminConfig adminConfigProperties;
    private static AdminConfig configProperties;

    @PostConstruct
    public void init() {
        configProperties = this.adminConfigProperties;
    }

    @Override
    public List<String> getUserNameList(String userName) {
        return tUserDao.getUserNameList(userName);
    }

    @Override
    public TUser selectByUnAndPsw(TUser tUser) {
        return tUserDao.selectByUnAndPsw(tUser);
    }

    @Override
    public TUser selectByUsername(String userName) {
        return tUserDao.selectByUsername(userName);
    }

    @Override
    public List<TUser> all() {
        return tUserDao.getAll();
    }

    @Override
    public Map getInfo() {
        List<TUser> users = all();
        List<TDatabase> databases = tDatabaseService.all();
        Map map = new HashMap();
        map.put("user",users);
        map.put("database",databases);
        return map;
    }

    @Override
    public Map checkAdmin(TUser tUser) {
        if (tUser.getUser().equals(configProperties.getAdmin()) && tUser.getPassword().equals(configProperties.getAdmin())) {
            return getInfo();
        }
        return null;
    }

    @Override
    public void updateLog() {
        TUser user = tUserDao.selectByUsername("admin");
        updateById(user);
    }

    @Override
    public void updateData() {
        tUserDao.updateAll();
    }
}