package com.trusteeship.manage.service.dao;

import com.trusteeship.manage.service.base.itf.BaseDao;
import com.trusteeship.manage.service.bean.entity.TUser;
import com.trusteeship.manage.service.bean.page.TUserP;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author Jo
 * @email 124185954@qq.com
 * @date 2020-03-25 10:19:43
 */
@Repository
public interface TUserDao extends BaseDao<TUser,TUserP> {

    List<String> getUserNameList(String userName);

    TUser selectByUnAndPsw(TUser tUser);

    TUser selectByUsername(String userName);
}
