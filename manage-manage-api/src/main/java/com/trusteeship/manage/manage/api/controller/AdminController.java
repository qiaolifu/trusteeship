package com.trusteeship.manage.manage.api.controller;


import com.core.exception.ApiException;
import com.core.page.R;
import com.core.utils.DatabaseUtil;
import com.trusteeship.manage.service.base.RedisService;
import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.bean.entity.TUser;
import com.trusteeship.manage.service.common.constants.BizCode;
import com.trusteeship.manage.service.service.itf.TDatabaseService;
import com.trusteeship.manage.service.service.itf.TUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/manager/admin")
@Api(value = "")
public class AdminController extends BaseController {
    @Autowired
    private TDatabaseService tDatabaseService;
    @Autowired
    private TUserService tUserService;

    @PostMapping(value = "/getCom")
    @ApiOperation(value = "获取命令行")
    public R insert(HttpServletRequest request, @RequestBody TUser tUser) throws Exception{

        String u = checkToken(request);
        TUser user = tUserService.selectByUsername(u);
        if (!user.getStatus().equals(TUser.ADMIN)) {
            throw new ApiException(BizCode.IN_VALID_USER);
        }
        TDatabase database = tDatabaseService.selectByUsername(tUser.getUser());
        if(null == database)throw new ApiException(BizCode.DATABASE_NOT_BINDING);
        String com = DatabaseUtil.getXShellCom(database,tUser);

        return R.ok().put("com",com);
    }

}
