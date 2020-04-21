package com.trusteeship.manage.manage.api.controller;


import com.core.exception.ApiException;
import com.core.page.R;
import com.core.utils.DatabaseUtil;
import com.trusteeship.manage.service.bean.entity.Attachment;
import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.bean.entity.TUser;
import com.trusteeship.manage.service.common.constants.BizCode;
import com.trusteeship.manage.service.service.itf.TDatabaseService;
import com.trusteeship.manage.service.service.itf.TUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/manage/database")
@Api(value = "")
public class TDatabaseController extends BaseController {
    @Autowired
    private TDatabaseService tDatabaseService;
    @Autowired
    private TUserService tUserService;

    @PostMapping(value = "/listBackups")
    @ApiOperation(value = "获取备份列表")
    public R listBackups(HttpServletRequest request) throws InterruptedException {

        String userName = checkToken(request);
        TUser user = tUserService.selectByUsername(userName);

        if (user.getStatus().equals(TUser.INVALID)) {
            throw new ApiException(BizCode.EXPIRE_USER);
        }
        String filePath = "/trusteeship/" + userName + "/";
        List<String> filenameList = getFiles(filePath);
        return R.ok().put("filename", filenameList);
    }


    @PostMapping(value = "/download")
    @ApiOperation(value = "下载")
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody Attachment attachment) throws IOException {
        String username = checkToken(request);
        // 读到流中
        InputStream inStream = new FileInputStream("/trusteeship/" + username + "/" + attachment.getFilename() + ".sql");
        response.reset();
        response.setContentType("text/plain");
        response.addHeader("Content-Disposition", "attachment; filename=" + attachment.getFilename() + ".sql");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        byte[] b = new byte[100];
        int len;

        try {
            len = inStream.read(b);
            if (len <= 0)throw new ApiException(BizCode.LOG_DATA_NOT_EXIST);
            response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/recover")
    @ApiOperation(value = "恢复备份")
    public R recover(HttpServletRequest request,@RequestBody Attachment attachment) throws Exception {

        String userName = checkToken(request);
        TUser user = tUserService.selectByUsername(userName);
        if (!user.getStatus().equals(TUser.VIP)){
            throw new ApiException(BizCode.ONLY_VIP);
        }
        TDatabase database = tDatabaseService.selectByUsername(userName);
        DatabaseUtil.recover(attachment.getFilename(),user,database);
        return R.ok();
    }

    @PostMapping(value = "/dbList")
    @ApiOperation(value = "获取数据库列表")
    public R dbList(HttpServletRequest request) throws Exception {
        String userName = checkToken(request);
        TUser user = tUserService.selectByUsername(userName);
        List<String> list = tDatabaseService.dbList(user);
        return R.ok().put("db",list);
    }


    private static ArrayList<String> getFiles(String filepath) {
        ArrayList<String> files = new ArrayList<>();
        File file = new File(filepath);
        File[] tempLists = file.listFiles();
        if (null != tempLists && 0 != tempLists.length) {
            for (File tempList : tempLists) {
                if (tempList.isFile()) {
                    String[] s = tempList.toString().split("/");
                    String filename = s[s.length - 1].substring(0, s[s.length - 1].lastIndexOf("."));
                    files.add(filename);
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {
//        //添加文件路径
//        StringBuilder com = new StringBuilder();
//        com.append("mysql -u").append("user").append(" -p"
//        ).append("psw").append(" -h ").append("url"
//        ).append(" -e show databases; | grep -Ev Database|information_schema|mysql|performance_schema | xargs mysqldump -u"
//        ).append("user").append(" -p").append("psw"
//        ).append(" -h").append("url"
//        ).append(" --column-statistics=0 --databases > /trusteeship/").append("11111user").append("/"
//        ).append(DateUtil.parse2Str(new Date(),"yyyy-MM-dd_HH-mm-ss"));
//        System.out.println(com.toString());

        System.out.println("Jane Campion directed \"The Piano\" in 1993.");
    }
}
