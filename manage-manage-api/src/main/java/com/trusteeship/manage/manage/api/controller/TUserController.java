package com.trusteeship.manage.manage.api.controller;


import com.core.exception.ApiException;
import com.core.page.R;
import com.core.utils.DateUtil;
import com.trusteeship.manage.service.base.RedisService;
import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.bean.entity.TUser;
import com.trusteeship.manage.service.bean.page.TUserP;
import com.trusteeship.manage.service.common.constants.BizCode;
import com.trusteeship.manage.service.common.constants.RedisKey;
import com.trusteeship.manage.service.service.itf.TDatabaseService;
import com.trusteeship.manage.service.service.itf.TUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.core.utils.ParameterUtil.checkParameter;
import static com.core.utils.ParameterUtil.checkParameterUnAndPsw;

@RestController
@RequestMapping(value = "/api/user")
@Api(value = "")
public class TUserController extends BaseController {
    @Autowired
    private TUserService tUserService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private TDatabaseService tDatabaseService;

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册")
    public R insert(@RequestBody TUser tUser) {

        checkParameter(tUser);
        checkUserExist(tUser);
        tUser.setCreateTime(new Date());
        tUser.setStatus(TUser.NORMAL);
        tUser.setEffectTime(new Date());
        tUser.setVipTime(new Date());
        tUserService.insert(tUser);
        return R.ok();
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public R login(@RequestBody TUser tUser) {
        Map flag = tUserService.checkAdmin(tUser);
        if (null != flag) {
            tUserService.updateLog();
            return R.ok().put("super", flag);
        }
        TUser userA = tUserService.selectByUsername("admin");
        TUser user = loginCheck(tUser,userA);
        long time = System.currentTimeMillis();
        String token = UUID.randomUUID() + Long.toString(time);
        redisService.set(RedisKey.USER_KEY_TOKEN_CUSTOMER + token, tUser.getUser(), 30 * 60);
        Map map = new HashMap();
        map.put("status", user.getStatus());
        map.put("token", token);
        return R.ok().putAll(map);
    }


    @PostMapping(value = "/binding")
    @ApiOperation(value = "绑定数据库")
    public R binding(HttpServletRequest request, @RequestBody TDatabase database) {
        String u = checkToken(request);
        TUser user = tUserService.selectByUsername(u);
        TDatabase db = tDatabaseService.selectByUsername(u);
        if (null != db) {
            throw new ApiException(BizCode.DATABASE_EXIST);
        }
        checkParameter(database);
        checkDatabaseExist(database);
        database.setBelongs(user.getId());
        tDatabaseService.insert(database);
        return R.ok();
    }

    @PostMapping(value = "/list")
    @ApiOperation(value = "列表")
    public R list(HttpServletRequest request, @RequestBody TUserP tUser) {
        String u = checkToken(request);
        TUser user = tUserService.selectByUsername(u);
        if (!user.getStatus().equals(TUser.ADMIN)) {
            throw new ApiException(BizCode.IN_VALID_USER);
        }
        tUserService.list(tUser);
        return R.ok().put("users", tUser);
    }

    @PostMapping(value = "/detail")
    @ApiOperation(value = "详情")
    public R detail(HttpServletRequest request, @RequestBody TUser tUser) {
        String u = checkToken(request);
        TUser user = tUserService.selectByUsername(u);
        if (!user.getStatus().equals(TUser.ADMIN)) {
            throw new ApiException(BizCode.IN_VALID_USER);
        }
        TUser detail = tUserService.findById(tUser.getId());
        return R.ok().put("user", detail);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改")
    public R update(HttpServletRequest request, @RequestBody TUser tUser) {
        String u = checkToken(request);
        TUser user = tUserService.selectByUsername(u);
        if (!user.getStatus().equals(TUser.ADMIN)) {
            throw new ApiException(BizCode.IN_VALID_USER);
        }
        tUserService.updateById(tUser);
        return R.ok();
    }


    private void checkUserExist(TUser tUser) {
        List<String> checkNames = tUserService.getUserNameList(tUser.getUser());

        if (checkNames.contains(tUser.getUser())) {
            throw new ApiException(BizCode.USERNAME_EXIST);
        }
    }

    private void checkDatabaseExist(TDatabase database) {
        List<String> checkIPs = tDatabaseService.getDatabaseIPList(database.getUrl());
        if (checkIPs.contains(database.getUrl())) {
            throw new ApiException(BizCode.IP_EXIST);
        }
    }

    private TUser loginCheck(TUser tUser,TUser userA) {
        checkParameterUnAndPsw(tUser);
        TUser user = tUserService.selectByUnAndPsw(tUser);

        if (null == user) {
            throw new ApiException(BizCode.ERROR_USER_PWD);
        }
        Date date = DateUtil.addDay(userA.getUpdateTime(), 30);
        if (date.getTime() < new Date().getTime()) {
            tUserService.updateData();
        }
        if (!user.getStatus().equals(TUser.ADMIN)) {
            checkUserStatus(user);
        }
        return user;
    }

    @Transactional
    public void checkUserStatus(TUser user) {
        Date date = new Date();
        if (user.getVipTime().getTime() > date.getTime()) {
            user.setStatus(TUser.VIP);
        } else {
            if (user.getEffectTime().getTime() > date.getTime()) {
                user.setStatus(TUser.NORMAL);
            } else {
                user.setStatus(TUser.INVALID);
            }
        }
        tUserService.updateById(user);
        if (user.getStatus().equals(TUser.INVALID)) {
            throw new ApiException(BizCode.EXPIRE_USER);
        }
    }

}

