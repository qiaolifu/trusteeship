package com.trusteeship.manage.manage.api.controller;


import com.core.exception.ApiException;
import com.core.page.R;
import com.core.utils.ParameterUtil;
import com.trusteeship.manage.service.base.RedisService;
import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.bean.entity.TUser;
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
    public R insert(@RequestBody TUser tUser) throws InterruptedException {

        checkParameter(tUser);
        checkUserExist(tUser);
        tUser.setCreateTime(new Date());
        tUser.setStatus(TUser.NORMAL);
        tUserService.insert(tUser);
        return R.ok();
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public R login(@RequestBody TUser tUser) throws InterruptedException {
        if (tUser.getUser().equals(TUser.ADMIN) && tUser.getPassword().equals(TUser.KEY)){
            Map map = tUserService.getInfo();
            return R.ok().put("super", map);
        }
        loginCheck(tUser);
        long time = System.currentTimeMillis();
        String token = UUID.randomUUID() + Long.toString(time);
        redisService.set(RedisKey.USER_KEY_TOKEN_CUSTOMER + token , tUser.getUser(), 30 * 60);
        return R.ok().put("token", token);
    }

    @PostMapping(value = "/binding")
    @ApiOperation(value = "绑定数据库")
    public R binding(HttpServletRequest request,@RequestBody TDatabase database) throws InterruptedException {
        String u = checkToken(request);
        TUser user = tUserService.selectByUsername(u);
        TDatabase db = tDatabaseService.selectByUsername(u);
        if (null != db){
            throw new ApiException(BizCode.DATABASE_EXIST);
        }
        checkParameter(database);
        checkDatabaseExist(database);
        database.setBelongs(user.getId());
        tDatabaseService.insert(database);
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

    private void loginCheck(TUser tUser) {
        String regex = "^[a-z0-9A-Z]+$";
        checkParameterUnAndPsw(tUser, regex);
        TUser user = tUserService.selectByUnAndPsw(tUser);
        if (null == user){
            throw new ApiException(BizCode.ERROR_USER_PWD);
        }
        checkUserStatus(user);
    }

    @Transactional
    public void checkUserStatus(TUser user) {
        if (user.getStatus().equals(TUser.INVALID)) {
            throw new ApiException(BizCode.EXPIRE_USER);
        }
        if (user.getStatus().equals(TUser.VIP)){
            if (user.getEffectTime().getTime() < new Date().getTime()){
                user.setStatus(TUser.NORMAL);
                tUserService.updateById(user);
            }
        }if (user.getStatus().equals(TUser.NORMAL)){
            System.out.println("id为：" + user.getId() + "登入");
        }
    }
}
