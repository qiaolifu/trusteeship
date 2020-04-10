package com.trusteeship.manage.service.service.itf;

import com.trusteeship.manage.service.base.itf.BaseService;
import com.trusteeship.manage.service.bean.entity.TUser;
import com.trusteeship.manage.service.bean.page.TUserP;

import java.util.List;


public interface TUserService extends BaseService<TUser, TUserP> {

    List<String> getUserNameList(String user);

    TUser selectByUnAndPsw(TUser tUser);

    TUser selectByUsername(String userName);
}




