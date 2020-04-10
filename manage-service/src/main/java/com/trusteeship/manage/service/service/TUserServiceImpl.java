package com.trusteeship.manage.service.service;

import org.springframework.stereotype.Service;
import com.trusteeship.manage.service.bean.entity.TUser;
import com.trusteeship.manage.service.bean.page.TUserP;
import com.trusteeship.manage.service.dao.TUserDao;
import com.trusteeship.manage.service.service.itf.TUserService;
import com.trusteeship.manage.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tUserService")
public class TUserServiceImpl extends BaseServiceImpl<TUser, TUserP> implements TUserService {

    @Autowired
    private TUserDao tUserDao;

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
}